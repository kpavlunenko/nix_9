package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.dao.CompanyDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;
    private final AgreementDao agreementDao;

    public CompanyServiceImpl(CompanyDao companyDao, AgreementDao agreementDao) {
        this.companyDao = companyDao;
        this.agreementDao = agreementDao;
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
        long countAgreements =  agreementDao.countByCompany(id);
        if (countAgreements != 0) {
            throw new EntityNotFoundException("With this company exist " + countAgreements + " agreements, you can't delete this company.");
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
    public ResponseDataTable<Company> findAll(RequestDataTable request) {
        ResponseDataTable<Company> responseDataTable = companyDao.findAll(request);
        long count = companyDao.count();
        long entriesFrom = ((request.getCurrentPage() - 1) * request.getPageSize()) + 1;
        long entriesTo = request.getCurrentPage() * request.getPageSize();
        long totalPageSize = 0;
        if (count % request.getPageSize() == 0) {
            totalPageSize = count / request.getPageSize();
        } else {
            totalPageSize = count / request.getPageSize() + 1;
        }
        entriesTo = Math.min(entriesTo, count);
        responseDataTable.setItemsSize(count);
        responseDataTable.setEntriesFrom(entriesFrom);
        responseDataTable.setEntriesTo(entriesTo);
        responseDataTable.setTotalPageSize(totalPageSize);
        return responseDataTable;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = companyDao.findAll();
        return companies;
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
