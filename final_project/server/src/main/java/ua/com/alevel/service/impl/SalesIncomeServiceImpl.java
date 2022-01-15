package ua.com.alevel.service.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudTableRepositoryHelper;
import ua.com.alevel.persistence.entity.register.SalesIncome;
import ua.com.alevel.persistence.repository.SalesIncomeRepository;
import ua.com.alevel.service.SalesIncomeService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalesIncomeServiceImpl implements SalesIncomeService {

    private final CrudTableRepositoryHelper<SalesIncome, SalesIncomeRepository> repositoryHelper;
    private final SalesIncomeRepository salesIncomeRepository;

    public SalesIncomeServiceImpl(CrudTableRepositoryHelper<SalesIncome, SalesIncomeRepository> repositoryHelper, SalesIncomeRepository salesIncomeRepository) {
        this.repositoryHelper = repositoryHelper;
        this.salesIncomeRepository = salesIncomeRepository;
    }

    @Override
    @Transactional
    public void create(SalesIncome entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(salesIncomeRepository, entity);
    }

    @Override
    @Transactional
    public void update(SalesIncome entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(salesIncomeRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(salesIncomeRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesIncome> findById(Long id) {
        return repositoryHelper.findById(salesIncomeRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesIncome> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(salesIncomeRepository, parameterMap, SalesIncome.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(salesIncomeRepository, parameterMap, SalesIncome.class);
    }

    @Override
    @Transactional
    public void deleteBySalesInvoice(Long id) {
        salesIncomeRepository.deleteAllBySalesInvoice_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesIncome> getSalesIncomeByPeriod(Map<String, String[]> parameterMap) {
        List<SalesIncome> salesIncomes = repositoryHelper.findAll(salesIncomeRepository, parameterMap, SalesIncome.class);
        salesIncomes.forEach(salesIncome -> salesIncome.setDate(DateUtils.truncate(salesIncome.getDate(), Calendar.DATE)));
        return salesIncomes;
    }

    private void checkInputDataOnValid(SalesIncome entity) {
        if (entity.getQuantity() == BigDecimal.ZERO) {
            throw new IncorrectInputData("quantity can not be 0");
        }
    }
}
