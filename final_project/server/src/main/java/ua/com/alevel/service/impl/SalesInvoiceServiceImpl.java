package ua.com.alevel.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.document.PurchaseInvoice;
import ua.com.alevel.persistence.entity.document.SalesInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.PurchaseInvoiceGood;
import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;
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

    private final CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final StockOfGoodService stockOfGoodService;

    public SalesInvoiceServiceImpl(CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper, SalesInvoiceRepository salesInvoiceRepository, StockOfGoodService stockOfGoodService) {
        this.repositoryHelper = repositoryHelper;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.stockOfGoodService = stockOfGoodService;
    }

    @Override
    @Transactional
    public void create(SalesInvoice entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(salesInvoiceRepository, entity);
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void update(SalesInvoice entity) {
        checkInputDataOnValid(entity);
        stockOfGoodService.deleteByDocumentIdAndName(entity.getId(), entity.getClass().getSimpleName());
        repositoryHelper.update(salesInvoiceRepository, entity);
        repositoryHelper.create(salesInvoiceRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        stockOfGoodService.deleteByDocumentIdAndName(id, SalesInvoice.class.getSimpleName());
        repositoryHelper.delete(salesInvoiceRepository, id);
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
        for (SalesInvoiceGood salesInvoiceGood : entity.getSalesInvoiceGoods()) {
            if (salesInvoiceGood.getNomenclature().getService()) {
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

                if (salesQuantity == 0) {
                    break;
                }
            }
            if (salesQuantity != 0) {
                throw new IncorrectInputData("for nomenclature:" + salesInvoiceGood.getNomenclature().getName() + " not enough quantity: " + salesQuantity);
            }
        }
    }

    private void checkInputDataOnValid(SalesInvoice entity) {
        if (CollectionUtils.isEmpty(entity.getSalesInvoiceGoods())) {
            throw new IncorrectInputData("tabular part is empty");
        } else {
            for (SalesInvoiceGood salesInvoiceGood : entity.getSalesInvoiceGoods()) {
                if (salesInvoiceGood.getPrice() == BigDecimal.ZERO) {
                    throw new IncorrectInputData("price can not be 0");
                }
                if (salesInvoiceGood.getQuantity() == BigDecimal.ZERO) {
                    throw new IncorrectInputData("quantity can not be 0");
                }
                if (salesInvoiceGood.getSum() == BigDecimal.ZERO) {
                    throw new IncorrectInputData("sum can not be 0");
                }
            }
        }
    }
}
