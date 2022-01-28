package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.BusinessDirection;
import ua.com.alevel.persistence.repository.BusinessDirectionRepository;
import ua.com.alevel.service.BusinessDirectionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BusinessDirectionServiceImpl implements BusinessDirectionService {

    private final CrudRepositoryHelper<BusinessDirection, BusinessDirectionRepository> repositoryHelper;
    private final BusinessDirectionRepository businessDirectionRepository;
    private final LoggerService loggerService;

    public BusinessDirectionServiceImpl(CrudRepositoryHelper<BusinessDirection, BusinessDirectionRepository> repositoryHelper,
                                        BusinessDirectionRepository businessDirectionRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.businessDirectionRepository = businessDirectionRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional()
    public void create(BusinessDirection entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(businessDirectionRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void update(BusinessDirection entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(businessDirectionRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + BusinessDirection.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(businessDirectionRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + BusinessDirection.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessDirection> findById(Long id) {
        return repositoryHelper.findById(businessDirectionRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessDirection> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(businessDirectionRepository, parameterMap, BusinessDirection.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(businessDirectionRepository, parameterMap, BusinessDirection.class);
    }

    private void checkInputDataOnValid(BusinessDirection entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
