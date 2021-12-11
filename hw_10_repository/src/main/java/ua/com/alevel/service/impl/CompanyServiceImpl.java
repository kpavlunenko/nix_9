package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Company;
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

    public CompanyServiceImpl(CrudRepositoryHelper<Company, CompanyRepository> repositoryHelper,
                              CompanyRepository companyRepository,
                              AgreementService agreementService) {
        this.repositoryHelper = repositoryHelper;
        this.companyRepository = companyRepository;
        this.agreementService = agreementService;
    }

    @Override
    @Transactional
    public void create(Company entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(companyRepository, entity);
    }

    @Override
    @Transactional
    public void update(Company entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(companyRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Map<String, String[]> params = new HashMap<>();
        params.put("company", new String[]{String.valueOf(id)});
        long countAgreements = agreementService.count(params);
        if (countAgreements != 0) {
            throw new EntityNotFoundException("With this company exist " + countAgreements + " agreements you can not delete this company");
        }
        repositoryHelper.delete(companyRepository, id);
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
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
