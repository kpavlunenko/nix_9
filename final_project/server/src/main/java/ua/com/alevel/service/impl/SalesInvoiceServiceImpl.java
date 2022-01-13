package ua.com.alevel.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.document.SalesInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;
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

    private final CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final StockOfGoodService stockOfGoodService;
    private final SalesIncomeService salesIncomeService;

    public SalesInvoiceServiceImpl(CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper, SalesInvoiceRepository salesInvoiceRepository, StockOfGoodService stockOfGoodService, SalesIncomeService salesIncomeService) {
        this.repositoryHelper = repositoryHelper;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.stockOfGoodService = stockOfGoodService;
        this.salesIncomeService = salesIncomeService;
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
        salesIncomeService.deleteBySalesInvoice(entity.getId());
        repositoryHelper.update(salesInvoiceRepository, entity);
        createRecordsForTableStockAndGoods(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        stockOfGoodService.deleteByDocumentIdAndName(id, SalesInvoice.class.getSimpleName());
        salesIncomeService.deleteBySalesInvoice(id);
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
            SalesIncome salesIncome = new SalesIncome();
            salesIncome.setCompany(entity.getCompany());
            salesIncome.setDate(entity.getDate());
            salesIncome.setSalesInvoice(entity);
            salesIncome.setQuantity(salesInvoiceGood.getQuantity());
            salesIncome.setRevenue(salesInvoiceGood.getSum());
            salesIncome.setNomenclature(salesInvoiceGood.getNomenclature());

            if (salesInvoiceGood.getNomenclature().getService()) {
                salesIncome.setCostPrice(BigDecimal.ZERO);
                salesIncome.setProfit(salesInvoiceGood.getSum());
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
            salesIncome.setProfit(BigDecimal.valueOf(salesInvoiceGood.getSum().floatValue() - costPrice * - 1));
            salesIncomeService.create(salesIncome);
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
