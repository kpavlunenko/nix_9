package ua.com.alevel.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.persistence.entity.register.StockOfGood;
import ua.com.alevel.persistence.repository.PurchaseInvoiceRepository;
import ua.com.alevel.service.CurrencyRateService;
import ua.com.alevel.service.PurchaseInvoiceService;
import ua.com.alevel.service.StockOfGoodService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

    @Value("${accountingCurrency.code}")
    private String accountingCurrencyCode;

    private final CrudRepositoryHelper<PurchaseInvoice, PurchaseInvoiceRepository> repositoryHelper;
    private final PurchaseInvoiceRepository purchaseInvoiceRepository;
    private final StockOfGoodService stockOfGoodService;
    private final CurrencyRateService currencyRateService;
    private final LoggerService loggerService;

    public PurchaseInvoiceServiceImpl(CrudRepositoryHelper<PurchaseInvoice, PurchaseInvoiceRepository> repositoryHelper,
                                      PurchaseInvoiceRepository purchaseInvoiceRepository,
                                      StockOfGoodService stockOfGoodService,
                                      CurrencyRateService currencyRateService, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.stockOfGoodService = stockOfGoodService;
        this.currencyRateService = currencyRateService;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(PurchaseInvoice entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(purchaseInvoiceRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void update(PurchaseInvoice entity) {
        checkInputDataOnValid(entity);
        stockOfGoodService.deleteByDocumentIdAndName(entity.getId(), entity.getClass().getSimpleName());
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(purchaseInvoiceRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        stockOfGoodService.deleteByDocumentIdAndName(id, PurchaseInvoice.class.getSimpleName());
        loggerService.commit(LoggerLevel.WARN, "object: " + PurchaseInvoice.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(purchaseInvoiceRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + PurchaseInvoice.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseInvoice> findById(Long id) {
        return repositoryHelper.findById(purchaseInvoiceRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseInvoice> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(purchaseInvoiceRepository, parameterMap, PurchaseInvoice.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(purchaseInvoiceRepository, parameterMap, PurchaseInvoice.class);
    }

    private void createRecordsForTableStockAndGoods(PurchaseInvoice entity) {
        float rate = 1;
        if (!entity.getCurrency().getCode().equals(accountingCurrencyCode)) {
            Optional<CurrencyRate> currencyRateOptional = currencyRateService.findByDateAndAndCurrencyId(entity.getDate(), entity.getCurrency().getId());
            if (currencyRateOptional.isEmpty()) {
                loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "Can not find rate for currency: " + entity.getCurrency().getName());
                throw new IncorrectInputData("Can not find rate for currency: " + entity.getCurrency().getName());
            }
            rate = currencyRateOptional.get().getRate().floatValue() / currencyRateOptional.get().getFrequencyRate();
        }

        for (PurchaseInvoiceGood purchaseInvoiceGood : entity.getPurchaseInvoiceGoods()) {
            if (purchaseInvoiceGood.getNomenclature().getService()) {
                continue;
            }
            StockOfGood stockOfGood = new StockOfGood();
            stockOfGood.setDate(entity.getDate());
            stockOfGood.setDocumentId(entity.getId());
            stockOfGood.setDocumentName(entity.getClass().getSimpleName());
            stockOfGood.setCompany(entity.getCompany());
            stockOfGood.setNomenclature(purchaseInvoiceGood.getNomenclature());
            stockOfGood.setQuantity(purchaseInvoiceGood.getQuantity());
            stockOfGood.setCost(BigDecimal.valueOf(purchaseInvoiceGood.getSum().floatValue() * rate));
            stockOfGood.setConsignment(entity);
            stockOfGoodService.create(stockOfGood);
        }
    }

    private void checkInputDataOnValid(PurchaseInvoice entity) {
        if (CollectionUtils.isEmpty(entity.getPurchaseInvoiceGoods())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "tabular part is empty");
            throw new IncorrectInputData("tabular part is empty");
        } else {
            for (PurchaseInvoiceGood purchaseInvoiceGood : entity.getPurchaseInvoiceGoods()) {
                if (purchaseInvoiceGood.getPrice() == BigDecimal.ZERO) {
                    loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "price can not be 0");
                    throw new IncorrectInputData("price can not be 0");
                }
                if (purchaseInvoiceGood.getQuantity() == BigDecimal.ZERO) {
                    loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "quantity can not be 0");
                    throw new IncorrectInputData("quantity can not be 0");
                }
                if (purchaseInvoiceGood.getSum() == BigDecimal.ZERO) {
                    loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "sum can not be 0");
                    throw new IncorrectInputData("sum can not be 0");
                }
            }
        }
    }
}
