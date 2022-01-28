package ua.com.alevel.service.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
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
    private final LoggerService loggerService;

    public SalesIncomeServiceImpl(CrudTableRepositoryHelper<SalesIncome, SalesIncomeRepository> repositoryHelper, SalesIncomeRepository salesIncomeRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.salesIncomeRepository = salesIncomeRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(SalesIncome entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(salesIncomeRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(SalesIncome entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(salesIncomeRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + SalesIncome.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(salesIncomeRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + SalesIncome.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
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
        loggerService.commit(LoggerLevel.WARN, "object: " + SalesIncome.class.getSimpleName() + "; stage: start; operation: deleteBySalesInvoice; id = " + id);
        salesIncomeRepository.deleteAllBySalesInvoice_Id(id);
        loggerService.commit(LoggerLevel.WARN, "object: " + SalesIncome.class.getSimpleName() + "; stage: finish; operation: deleteBySalesInvoice; id = " + id);

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
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "quantity is not valid");
            throw new IncorrectInputData("quantity can not be 0");
        }
    }
}
