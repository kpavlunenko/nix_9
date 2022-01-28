package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.PriceType;
import ua.com.alevel.persistence.repository.PriceRepository;
import ua.com.alevel.persistence.repository.PriceTypeRepository;
import ua.com.alevel.service.PriceTypeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PriceTypeServiceImpl implements PriceTypeService {

    private final CrudRepositoryHelper<PriceType, PriceTypeRepository> repositoryHelper;
    private final PriceTypeRepository priceTypeRepository;
    private final PriceRepository priceRepository;
    private final LoggerService loggerService;

    public PriceTypeServiceImpl(CrudRepositoryHelper<PriceType, PriceTypeRepository> repositoryHelper, PriceTypeRepository priceTypeRepository, PriceRepository priceRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.priceTypeRepository = priceTypeRepository;
        this.priceRepository = priceRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(PriceType entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(priceTypeRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(PriceType entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(priceTypeRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + PriceType.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        priceRepository.deleteAllByPriceType_Id(id);
        repositoryHelper.delete(priceTypeRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + PriceType.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceType> findById(Long id) {
        return repositoryHelper.findById(priceTypeRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PriceType> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(priceTypeRepository, parameterMap, PriceType.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(priceTypeRepository, parameterMap, PriceType.class);
    }

    private void checkInputDataOnValid(PriceType entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
