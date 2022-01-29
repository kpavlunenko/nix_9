package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.Counterparty;
import ua.com.alevel.persistence.repository.CounterpartyRepository;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CounterpartyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CrudRepositoryHelper<Counterparty, CounterpartyRepository> repositoryHelper;
    private final CounterpartyRepository counterpartyRepository;
    private final AgreementService agreementService;
    private final LoggerService loggerService;

    public CounterpartyServiceImpl(CrudRepositoryHelper<Counterparty, CounterpartyRepository> repositoryHelper,
                                   CounterpartyRepository counterpartyRepository,
                                   AgreementService agreementService, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.counterpartyRepository = counterpartyRepository;
        this.agreementService = agreementService;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional()
    public void create(Counterparty entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(counterpartyRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void update(Counterparty entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(counterpartyRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        Map<String, String[]> params = new HashMap<>();
        params.put("counterparty", new String[]{String.valueOf(id)});
        long countAgreements =  agreementService.count(params);
        if (countAgreements != 0) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + Counterparty.class.getSimpleName() + "; operation: update/new; id = " + id + "; problem = " + "With this counterparty exist " + countAgreements + " agreements you can not delete this counterparty");
            throw new IncorrectInputData("With this counterparty exist " + countAgreements + " agreements you can not delete this counterparty");
        }
        loggerService.commit(LoggerLevel.WARN, "object: " + Counterparty.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(counterpartyRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Counterparty.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Counterparty> findById(Long id) {
        return repositoryHelper.findById(counterpartyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Counterparty> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(counterpartyRepository, parameterMap, Counterparty.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(counterpartyRepository, parameterMap, Counterparty.class);
    }

    private void checkInputDataOnValid(Counterparty entity) {
        if (!innIsValid(entity.getInn())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "inn is not valid");
            throw new IncorrectInputData("inn is not valid");
        }
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean innIsValid(String inn) {
        return inn.matches("[0-9]+") && inn.length() == 12;
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
