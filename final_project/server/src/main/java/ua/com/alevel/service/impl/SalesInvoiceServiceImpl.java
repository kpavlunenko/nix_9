package ua.com.alevel.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.document.SalesInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;
import ua.com.alevel.persistence.repository.*;
import ua.com.alevel.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {

    private final CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper;
    private final SalesInvoiceRepository salesInvoiceRepository;


    public SalesInvoiceServiceImpl(CrudRepositoryHelper<SalesInvoice, SalesInvoiceRepository> repositoryHelper, SalesInvoiceRepository salesInvoiceRepository) {
        this.repositoryHelper = repositoryHelper;
        this.salesInvoiceRepository = salesInvoiceRepository;
    }

    @Override
    @Transactional
    public void create(SalesInvoice entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(salesInvoiceRepository, entity);
    }

    @Override
    @Transactional
    public void update(SalesInvoice entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(salesInvoiceRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
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
