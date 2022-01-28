package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.Agreement;
import ua.com.alevel.persistence.repository.AgreementRepository;
import ua.com.alevel.service.AgreementService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final CrudRepositoryHelper<Agreement, AgreementRepository> repositoryHelper;
    private final AgreementRepository agreementRepository;
    private final LoggerService loggerService;

    public AgreementServiceImpl(CrudRepositoryHelper<Agreement, AgreementRepository> repositoryHelper,
                                AgreementRepository agreementRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.agreementRepository = agreementRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional()
    public void create(Agreement entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(agreementRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void update(Agreement entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(agreementRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + Agreement.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(agreementRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Agreement.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agreement> findById(Long id) {
        return repositoryHelper.findById(agreementRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agreement> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(agreementRepository, parameterMap, Agreement.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(agreementRepository, parameterMap, Agreement.class);
    }

    private void checkInputDataOnValid(Agreement entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
