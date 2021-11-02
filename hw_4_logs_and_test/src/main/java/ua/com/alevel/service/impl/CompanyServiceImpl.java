package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CompanyDao;
import ua.com.alevel.dao.impl.CompanyDaoImpl;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CustomerAgreementService;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public void create(Company entity) {
        companyDao.create(entity);
    }

    @Override
    public void update(Company entity) {
        companyDao.update(entity);
    }

    @Override
    public void delete(String id) {
        CustomerAgreementService customerAgreementService = new CustomerAgreementServiceImpl();
        CustomerAgreement[] customerAgreements = customerAgreementService.findAll();
        boolean allowToDelete = true;
        for (int i = 0; i < customerAgreements.length; i++) {
            if (customerAgreements[i] != null && customerAgreements[i].getCompany().getId().equals(id)) {
                if(allowToDelete){
                    System.out.println("For current company exist customer agreements, operation 'delete' isn't allowed!");
                    System.out.println("You need to delete next agreements:");
                    allowToDelete = false;
                }
                System.out.println(customerAgreements[i].getId());
            }
        }
        if(allowToDelete) {
            companyDao.delete(id);
        }
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public Company[] findAll() {
        return companyDao.findAll();
    }
}
