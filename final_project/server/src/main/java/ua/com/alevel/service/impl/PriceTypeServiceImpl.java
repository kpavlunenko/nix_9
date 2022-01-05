package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.PriceType;
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

    public PriceTypeServiceImpl(CrudRepositoryHelper<PriceType, PriceTypeRepository> repositoryHelper, PriceTypeRepository priceTypeRepository, PriceRepository priceRepository) {
        this.repositoryHelper = repositoryHelper;
        this.priceTypeRepository = priceTypeRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    @Transactional
    public void create(PriceType entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(priceTypeRepository, entity);
    }

    @Override
    @Transactional
    public void update(PriceType entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(priceTypeRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        priceRepository.deleteAllByPriceType_Id(id);
        repositoryHelper.delete(priceTypeRepository, id);
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
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
