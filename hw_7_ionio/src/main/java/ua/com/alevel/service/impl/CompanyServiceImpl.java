package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CompanyDao;
import ua.com.alevel.dao.impl.CompanyDaoImpl;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CustomerAgreementService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public void create(Company entity) {
        companyDao.create(entity);
    }

    @Override
    public void update(Company entity) {
        try {
            companyDao.update(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        CustomerAgreementService customerAgreementService = new CustomerAgreementServiceImpl();
        List<CustomerAgreement> customerAgreements = customerAgreementService.findAll();
        boolean allowToDelete = true;
        if (customerAgreements.stream().anyMatch(customerAgreement -> customerAgreement.getCompany().getId().equals(id))) {
            System.out.println("For current company exist customer agreements, operation 'delete' isn't allowed!");
            System.out.println("You need to delete next agreements:");
            allowToDelete = false;
            customerAgreements.stream()
                    .filter(customerAgreement -> customerAgreement.getCompany().getId().equals(id))
                    .forEach(customerAgreement -> System.out.println(customerAgreement.getId()));
        }
        if (allowToDelete) {
            try {
                companyDao.delete(id);
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }

    @Override
    public Company findById(String id) {
        try {
            return companyDao.findById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }
}
