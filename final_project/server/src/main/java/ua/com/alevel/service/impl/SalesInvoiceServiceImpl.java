package ua.com.alevel.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.document.SalesInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.persistence.entity.register.SalesIncome;
import ua.com.alevel.persistence.entity.register.StockOfGood;
import ua.com.alevel.persistence.repository.*;
import ua.com.alevel.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {

    @Value("${accountingCurrency.code}")
    private String accountingCurrencyCode;

    private final CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final StockOfGoodService stockOfGoodService;
    private final CurrencyRateService currencyRateService;
    private final SalesIncomeService salesIncomeService;
    private final LoggerService loggerService;

    public SalesInvoiceServiceImpl(CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper, SalesInvoiceRepository salesInvoiceRepository, StockOfGoodService stockOfGoodService, CurrencyRateService currencyRateService, SalesIncomeService salesIncomeService, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.stockOfGoodService = stockOfGoodService;
        this.currencyRateService = currencyRateService;
        this.salesIncomeService = salesIncomeService;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(SalesInvoice entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(salesInvoiceRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void update(SalesInvoice entity) {
        checkInputDataOnValid(entity);
        stockOfGoodService.deleteByDocumentIdAndName(entity.getId(), entity.getClass().getSimpleName());
        salesIncomeService.deleteBySalesInvoice(entity.getId());
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(salesInvoiceRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        stockOfGoodService.deleteByDocumentIdAndName(id, SalesInvoice.class.getSimpleName());
        salesIncomeService.deleteBySalesInvoice(id);
        loggerService.commit(LoggerLevel.WARN, "object: " + SalesInvoice.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(salesInvoiceRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + SalesInvoice.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesInvoice> findById(Long id) {
        return repositoryHelper.findById(salesInvoiceRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesInvoice> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(salesInvoiceRepository, parameterMap, SalesInvoice.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(salesInvoiceRepository, parameterMap, SalesInvoice.class);
    }

    private void createRecordsForTableStockAndGoods(SalesInvoice entity) {
        float rate = 1;
        if (!entity.getCurrency().getCode().equals(accountingCurrencyCode)) {
            Optional<CurrencyRate> currencyRateOptional = currencyRateService.findByDateAndAndCurrencyId(entity.getDate(), entity.getCurrency().getId());
            if (currencyRateOptional.isEmpty()) {
                loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + ";" + "Can not find rate for currency: " + entity.getCurrency().getName());
                throw new IncorrectInputData("Can not find rate for currency: " + entity.getCurrency().getName());
            }
            rate = currencyRateOptional.get().getRate().floatValue() / currencyRateOptional.get().getFrequencyRate();
        }

        for (SalesInvoiceGood salesInvoiceGood : entity.getSalesInvoiceGoods()) {
            SalesIncome salesIncome = new SalesIncome();
            salesIncome.setCompany(entity.getCompany());
            salesIncome.setDate(entity.getDate());
            salesIncome.setSalesInvoice(entity);
            salesIncome.setQuantity(salesInvoiceGood.getQuantity());
            salesIncome.setNomenclature(salesInvoiceGood.getNomenclature());
            salesIncome.setRevenue(BigDecimal.valueOf(salesInvoiceGood.getSum().doubleValue() * rate));

            if (salesInvoiceGood.getNomenclature().getService()) {
                salesIncome.setCostPrice(BigDecimal.ZERO);
                salesIncome.setProfit(BigDecimal.valueOf(salesInvoiceGood.getSum().doubleValue() * rate));
                salesIncomeService.create(salesIncome);
                continue;
            }
            List<StockOfGood> stockOfFreeGoods = stockOfGoodService.getStockOfGoodByNomenclatureIdAndCompanyId(salesInvoiceGood.getNomenclature().getId(),
                    entity.getCompany().getId(),
                    entity.getDate());
            stockOfFreeGoods = stockOfFreeGoods.stream().filter(stockOfGood -> stockOfGood.getQuantity().floatValue() > 0).collect(Collectors.toList());
            if (stockOfFreeGoods.isEmpty()) {
                throw new IncorrectInputData("for nomenclature:" + salesInvoiceGood.getNomenclature().getName() + " not enough quantity");
            }
            float salesQuantity = salesInvoiceGood.getQuantity().floatValue();
            float quantityForOut = 0;
            float costPrice = 0;
            for (StockOfGood stockOfFreeGood : stockOfFreeGoods) {

                StockOfGood stockOfGood = new StockOfGood();
                stockOfGood.setDate(entity.getDate());
                stockOfGood.setDocumentId(entity.getId());
                stockOfGood.setDocumentName(entity.getClass().getSimpleName());
                stockOfGood.setCompany(entity.getCompany());
                stockOfGood.setNomenclature(salesInvoiceGood.getNomenclature());
                stockOfGood.setConsignment(stockOfFreeGood.getConsignment());
                quantityForOut = Math.min(stockOfFreeGood.getQuantity().floatValue(), salesQuantity);
                salesQuantity = salesQuantity - quantityForOut;
                stockOfGood.setQuantity(BigDecimal.valueOf(quantityForOut).negate());
                stockOfGood.setCost(BigDecimal.valueOf(quantityForOut * stockOfFreeGood.getCost().floatValue() / stockOfFreeGood.getQuantity().floatValue()).negate());
                stockOfGoodService.create(stockOfGood);

                costPrice = costPrice + stockOfGood.getCost().floatValue();

                if (salesQuantity == 0) {
                    break;
                }
            }
            if (salesQuantity != 0) {
                throw new IncorrectInputData("for nomenclature:" + salesInvoiceGood.getNomenclature().getName() + " not enough quantity: " + salesQuantity);
            }

            salesIncome.setCostPrice(BigDecimal.valueOf(costPrice * - 1));
            salesIncome.setProfit(BigDecimal.valueOf(salesInvoiceGood.getSum().floatValue() * rate - costPrice * - 1));
            salesIncomeService.create(salesIncome);
        }
    }

    private void checkInputDataOnValid(SalesInvoice entity) {
        if (CollectionUtils.isEmpty(entity.getSalesInvoiceGoods())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "tabular part is empty");
            throw new IncorrectInputData("tabular part is empty");
        } else {
            for (SalesInvoiceGood salesInvoiceGood : entity.getSalesInvoiceGoods()) {
                if (salesInvoiceGood.getPrice() == BigDecimal.ZERO) {
                    loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "price can not be 0");
                    throw new IncorrectInputData("price can not be 0");
                }
                if (salesInvoiceGood.getQuantity() == BigDecimal.ZERO) {
                    loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "quantity can not be 0");
                    throw new IncorrectInputData("quantity can not be 0");
                }
                if (salesInvoiceGood.getSum() == BigDecimal.ZERO) {
                    loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "sum can not be 0");
                    throw new IncorrectInputData("sum can not be 0");
                }
            }
        }
    }
}
