package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.dao.CompanyDao;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;
    private final AgreementService agreementService;

    public CompanyServiceImpl(CompanyDao companyDao, AgreementService agreementService) {
        this.companyDao = companyDao;
        this.agreementService = agreementService;
    }

    @Override
    public void create(Company entity) {
        checkInputDataOnValid(entity);
        companyDao.create(entity);
    }

    @Override
    public void update(Company entity) {
        if (!companyDao.existById(entity.getId())) {
            throw new EntityNotFoundException("company not found");
        }
        checkInputDataOnValid(entity);
        companyDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!companyDao.existById(id)) {
            throw new EntityNotFoundException("company not found");
        }
        Map<String, String[]> params = new HashMap<>();
        params.put("companyId", new String[]{String.valueOf(id)});
        long countAgreements =  agreementService.count(params);
        if (countAgreements != 0) {
            throw new EntityNotFoundException("With this company exist " + countAgreements + " agreements you can not delete this company");
        }
        companyDao.delete(id);
    }

    @Override
    public Company findById(Long id) {
        if (!companyDao.existById(id)) {
            throw new EntityNotFoundException("company not found");
        }
        return companyDao.findById(id);
    }

    @Override
    public List<Company> findAll(Map<String, String[]> parameterMap) {
        List<Company> companies = companyDao.findAll(parameterMap);
        return companies;
    }

    @Override
    public long count(Map<String, String[]> parameterMap) {
        return companyDao.count(parameterMap);
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
