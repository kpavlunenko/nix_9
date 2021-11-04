package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.dao.impl.CustomerDaoImpl;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CustomerAgreementService;
import ua.com.alevel.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void create(Customer entity) {
        LOGGER_INFO.info("object: Customer; stage: start; operation: create");
        customerDao.create(entity);
        LOGGER_INFO.info("object: Customer; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    public void update(Customer entity) {
        try {
            LOGGER_INFO.info("object: Customer; stage: start; operation: update; id = " + entity.getId());
            customerDao.update(entity);
            LOGGER_INFO.info("object: Customer; stage: finish; operation: update; id = " + entity.getId());
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: Customer; operation: update; id = " + entity.getId() + "; problem = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        CustomerAgreementService customerAgreementService = new CustomerAgreementServiceImpl();
        CustomerAgreement[] customerAgreements = customerAgreementService.findAll();
        boolean allowToDelete = true;
        for (int i = 0; i < customerAgreements.length; i++) {
            if (customerAgreements[i] != null && customerAgreements[i].getCustomer().getId().equals(id)) {
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
                LOGGER_WARN.warn("object: Customer; stage: start; operation: delete; id = " + id);
                customerDao.delete(id);
                LOGGER_WARN.warn("object: Customer; stage: finish; operation: delete; id = " + id);
            } catch (RuntimeException e) {
                LOGGER_ERROR.error("object: Customer; operation: delete; id = " + id + "; problem = " + e.getMessage());
                throw e;
            }
        }
    }

    @Override
    public Customer findById(String id) {
        try {
            return customerDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: Customer; operation: findById; id = " + id + "; problem = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Customer[] findAll() {
        return customerDao.findAll();
    }
}
