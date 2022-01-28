package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.directory.Company;
import ua.com.alevel.persistence.repository.CompanyRepository;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CrudRepositoryHelper<Company, CompanyRepository> repositoryHelper;
    private final CompanyRepository companyRepository;
    private final AgreementService agreementService;
    private final LoggerService loggerService;

    public CompanyServiceImpl(CrudRepositoryHelper<Company, CompanyRepository> repositoryHelper,
                              CompanyRepository companyRepository,
                              AgreementService agreementService, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.companyRepository = companyRepository;
        this.agreementService = agreementService;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(Company entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(companyRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Company entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(companyRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Map<String, String[]> params = new HashMap<>();
        params.put("company", new String[]{String.valueOf(id)});
        long countAgreements = agreementService.count(params);
        if (countAgreements != 0) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + Company.class.getSimpleName() + "; operation: delete; id = " + id + "; problem = " + "With this company exist " + countAgreements + " agreements you can not delete this company");
            throw new EntityNotFoundException("With this company exist " + countAgreements + " agreements you can not delete this company");
        }
        loggerService.commit(LoggerLevel.WARN, "object: " + Company.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(companyRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Company.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findById(Long id) {
        return repositoryHelper.findById(companyRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(companyRepository, parameterMap, Company.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(companyRepository, parameterMap, Company.class);
    }

    private void checkInputDataOnValid(Company entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
