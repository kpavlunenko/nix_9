package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Nomenclature;
import ua.com.alevel.persistence.repository.NomenclatureRepository;
import ua.com.alevel.service.NomenclatureService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

    private final CrudRepositoryHelper<Nomenclature, NomenclatureRepository> repositoryHelper;
    private final NomenclatureRepository nomenclatureRepository;

    public NomenclatureServiceImpl(CrudRepositoryHelper<Nomenclature, NomenclatureRepository> repositoryHelper, NomenclatureRepository nomenclatureRepository) {
        this.repositoryHelper = repositoryHelper;
        this.nomenclatureRepository = nomenclatureRepository;
    }

    @Override
    @Transactional
    public void create(Nomenclature entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(nomenclatureRepository, entity);
    }

    @Override
    @Transactional
    public void update(Nomenclature entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(nomenclatureRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(nomenclatureRepository, id);
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
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
