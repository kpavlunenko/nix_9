package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
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

    public AgreementServiceImpl(CrudRepositoryHelper<Agreement, AgreementRepository> repositoryHelper,
                                AgreementRepository agreementRepository) {
        this.repositoryHelper = repositoryHelper;
        this.agreementRepository = agreementRepository;
    }

    @Override
    @Transactional()
    public void create(Agreement entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(agreementRepository, entity);
    }

    @Override
    @Transactional()
    public void update(Agreement entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(agreementRepository, entity);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        repositoryHelper.delete(agreementRepository, id);
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
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
