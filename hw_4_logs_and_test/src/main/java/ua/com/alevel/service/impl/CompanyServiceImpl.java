package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CompanyDao;
import ua.com.alevel.dao.impl.CompanyDaoImpl;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CustomerAgreementService;

public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public void create(Company entity) {
        LOGGER_INFO.info("object: Company; stage: start; operation: create");
        companyDao.create(entity);
        LOGGER_INFO.info("object: Company; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    public void update(Company entity) {
        try {
            LOGGER_INFO.info("object: Company; stage: start; operation: update; id = " + entity.getId());
            companyDao.update(entity);
            LOGGER_INFO.info("object: Company; stage: finish; operation: update; id = " + entity.getId());
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: Company; operation: update; id = " + entity.getId() + "; problem = " + e.getMessage());
            throw e;
        }
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
            try {
                LOGGER_WARN.warn("object: Company; stage: start; operation: delete; id = " + id);
                companyDao.delete(id);
                LOGGER_WARN.warn("object: Company; stage: finish; operation: delete; id = " + id);
            } catch (RuntimeException e) {
                LOGGER_ERROR.error("object: Company; operation: delete; id = " + id + "; problem = " + e.getMessage());
                throw e;
            }
        }
    }

    @Override
    public Company findById(String id) {
        try {
            return companyDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: Company; operation: findById; id = " + id + "; problem = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Company[] findAll() {
        return companyDao.findAll();
    }
}
