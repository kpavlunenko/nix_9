package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.Nomenclature;
import ua.com.alevel.persistence.repository.NomenclatureRepository;
import ua.com.alevel.persistence.repository.PriceRepository;
import ua.com.alevel.service.NomenclatureService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

    private final CrudRepositoryHelper<Nomenclature, NomenclatureRepository> repositoryHelper;
    private final NomenclatureRepository nomenclatureRepository;
    private final PriceRepository priceRepository;
    private final LoggerService loggerService;

    public NomenclatureServiceImpl(CrudRepositoryHelper<Nomenclature, NomenclatureRepository> repositoryHelper, NomenclatureRepository nomenclatureRepository, PriceRepository priceRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.nomenclatureRepository = nomenclatureRepository;
        this.priceRepository = priceRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(Nomenclature entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(nomenclatureRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Nomenclature entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(nomenclatureRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + Nomenclature.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        priceRepository.deleteAllByNomenclature_Id(id);
        repositoryHelper.delete(nomenclatureRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Nomenclature.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nomenclature> findById(Long id) {
        return repositoryHelper.findById(nomenclatureRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nomenclature> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(nomenclatureRepository, parameterMap, Nomenclature.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(nomenclatureRepository, parameterMap, Nomenclature.class);
    }

    private void checkInputDataOnValid(Nomenclature entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
