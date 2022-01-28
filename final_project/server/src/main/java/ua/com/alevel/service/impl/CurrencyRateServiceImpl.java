package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudTableRepositoryHelper;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.persistence.repository.CurrencyRateRepository;
import ua.com.alevel.service.CurrencyRateService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CrudTableRepositoryHelper<CurrencyRate, CurrencyRateRepository> repositoryHelper;
    private final CurrencyRateRepository currencyRateRepository;
    private final LoggerService loggerService;

    public CurrencyRateServiceImpl(CrudTableRepositoryHelper<CurrencyRate, CurrencyRateRepository> repositoryHelper, CurrencyRateRepository currencyRateRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.currencyRateRepository = currencyRateRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(CurrencyRate entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(currencyRateRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(CurrencyRate entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(currencyRateRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + CurrencyRate.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(currencyRateRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + CurrencyRate.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyRate> findById(Long id) {
        return repositoryHelper.findById(currencyRateRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyRate> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(currencyRateRepository, parameterMap, CurrencyRate.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(currencyRateRepository, parameterMap, CurrencyRate.class);
    }

    private void checkInputDataOnValid(CurrencyRate entity) {
        if (entity.getRate() == BigDecimal.ZERO) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "rate can not be 0");
            throw new IncorrectInputData("rate can not be 0");
        }
        Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findByDateAndAndCurrency_Id(entity.getDate(), entity.getCurrency().getId());
        if (currencyRateOptional.isPresent() && currencyRateOptional.get().getId() != entity.getId()) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "currency rate with current currency is exist on this date");
            throw new IncorrectInputData("currency rate with current currency is exist on this date");
        }
    }

    @Override
    public Optional<CurrencyRate> findByDateAndAndCurrencyId(Date date, Long id) {
        Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findActualRateByDateAndAndCurrency_Id(id, date);
        if (currencyRateOptional.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + CurrencyRate.class.getSimpleName() + "; operation: update/new; id = " + id + "; problem = " + "Can not find rate for currency_Id: " + id);
            throw new IncorrectInputData("Can not find rate for currency_Id: " + id);
        }
        return currencyRateOptional;
    }
}
