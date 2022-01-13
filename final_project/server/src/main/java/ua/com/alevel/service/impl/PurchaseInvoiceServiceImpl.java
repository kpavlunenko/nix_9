package ua.com.alevel.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.persistence.entity.register.StockOfGood;
import ua.com.alevel.persistence.repository.PurchaseInvoiceRepository;
import ua.com.alevel.service.PurchaseInvoiceService;
import ua.com.alevel.service.StockOfGoodService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

    private final CrudRepositoryHelper<PurchaseInvoice, PurchaseInvoiceRepository> repositoryHelper;
    private final PurchaseInvoiceRepository purchaseInvoiceRepository;
    private final StockOfGoodService stockOfGoodService;

    public PurchaseInvoiceServiceImpl(CrudRepositoryHelper<PurchaseInvoice, PurchaseInvoiceRepository> repositoryHelper,
                                      PurchaseInvoiceRepository purchaseInvoiceRepository,
                                      StockOfGoodService stockOfGoodService
    ) {
        this.repositoryHelper = repositoryHelper;
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.stockOfGoodService = stockOfGoodService;
    }

    @Override
    @Transactional
    public void create(PurchaseInvoice entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(purchaseInvoiceRepository, entity);
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void update(PurchaseInvoice entity) {
        checkInputDataOnValid(entity);
        stockOfGoodService.deleteByDocumentIdAndName(entity.getId(), entity.getClass().getSimpleName());
        repositoryHelper.update(purchaseInvoiceRepository, entity);
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        stockOfGoodService.deleteByDocumentIdAndName(id, PurchaseInvoice.class.getSimpleName());
        repositoryHelper.delete(purchaseInvoiceRepository, id);
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
        for (PurchaseInvoiceGood purchaseInvoiceGood : entity.getPurchaseInvoiceGoods()) {
            StockOfGood stockOfGood = new StockOfGood();
            stockOfGood.setDate(entity.getDate());
            stockOfGood.setDocumentId(entity.getId());
            stockOfGood.setDocumentName(entity.getClass().getSimpleName());
            stockOfGood.setCompany(entity.getCompany());
            stockOfGood.setNomenclature(purchaseInvoiceGood.getNomenclature());
            stockOfGood.setQuantity(purchaseInvoiceGood.getQuantity());
            stockOfGood.setCost(purchaseInvoiceGood.getSum());
            stockOfGood.setConsignment(entity);
            stockOfGoodService.create(stockOfGood);
        }
    }

    private void checkInputDataOnValid(PurchaseInvoice entity) {
        if (CollectionUtils.isEmpty(entity.getPurchaseInvoiceGoods())) {
            throw new IncorrectInputData("tabular part is empty");
        } else {
            for (PurchaseInvoiceGood purchaseInvoiceGood : entity.getPurchaseInvoiceGoods()) {
                if (purchaseInvoiceGood.getPrice() == BigDecimal.ZERO) {
                    throw new IncorrectInputData("price can not be 0");
                }
                if (purchaseInvoiceGood.getQuantity() == BigDecimal.ZERO) {
                    throw new IncorrectInputData("quantity can not be 0");
                }
                if (purchaseInvoiceGood.getSum() == BigDecimal.ZERO) {
                    throw new IncorrectInputData("sum can not be 0");
                }
            }
        }
    }
}
