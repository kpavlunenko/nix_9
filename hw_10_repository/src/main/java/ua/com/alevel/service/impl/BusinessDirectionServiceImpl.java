package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.persistence.repository.BusinessDirectionRepository;
import ua.com.alevel.service.BusinessDirectionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BusinessDirectionServiceImpl implements BusinessDirectionService {

    private final CrudRepositoryHelper<BusinessDirection, BusinessDirectionRepository> repositoryHelper;
    private final BusinessDirectionRepository businessDirectionRepository;

    public BusinessDirectionServiceImpl(CrudRepositoryHelper<BusinessDirection, BusinessDirectionRepository> repositoryHelper,
                                        BusinessDirectionRepository businessDirectionRepository) {
        this.repositoryHelper = repositoryHelper;
        this.businessDirectionRepository = businessDirectionRepository;
    }

    @Override
    @Transactional()
    public void create(BusinessDirection entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(businessDirectionRepository, entity);
    }

    @Override
    @Transactional()
    public void update(BusinessDirection entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(businessDirectionRepository, entity);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        repositoryHelper.delete(businessDirectionRepository, id);
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
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
