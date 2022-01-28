package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.Currency;
import ua.com.alevel.persistence.repository.CurrencyRateRepository;
import ua.com.alevel.persistence.repository.CurrencyRepository;
import ua.com.alevel.service.CurrencyService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CrudRepositoryHelper<Currency, CurrencyRepository> repositoryHelper;
    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final LoggerService loggerService;

    public CurrencyServiceImpl(CrudRepositoryHelper<Currency, CurrencyRepository> repositoryHelper, CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(Currency entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(currencyRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Currency entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(currencyRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + Currency.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        currencyRateRepository.deleteAllByCurrency_Id(id);
        repositoryHelper.delete(currencyRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Currency.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> findById(Long id) {
        return repositoryHelper.findById(currencyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Currency> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(currencyRepository, parameterMap, Currency.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(currencyRepository, parameterMap, Currency.class);
    }

    private void checkInputDataOnValid(Currency entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
        if (!codeIsValid(entity.getCode())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "code is not valid");
            throw new IncorrectInputData("code is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }

    private boolean codeIsValid(String code) {
        return StringUtils.isNotEmpty(code) && StringUtils.isNotBlank(code);
    }
}
