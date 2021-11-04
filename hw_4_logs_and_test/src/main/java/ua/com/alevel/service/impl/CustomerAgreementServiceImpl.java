package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CustomerAgreementDao;
import ua.com.alevel.dao.impl.CustomerAgreementDaoImpl;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CustomerAgreementService;

public class CustomerAgreementServiceImpl implements CustomerAgreementService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final CustomerAgreementDao customerAgreementDao = new CustomerAgreementDaoImpl();

    @Override
    public void create(CustomerAgreement entity) {
        LOGGER_INFO.info("object: CustomerAgreement; stage: start; operation: create");
        customerAgreementDao.create(entity);
        LOGGER_INFO.info("object: CustomerAgreement; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    public void update(CustomerAgreement entity) {
        try {
            LOGGER_INFO.info("object: CustomerAgreement; stage: start; operation: update; id = " + entity.getId());
            customerAgreementDao.update(entity);
            LOGGER_INFO.info("object: CustomerAgreement; stage: finish; operation: update; id = " + entity.getId());
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: CustomerAgreement; operation: update; id = " + entity.getId() + "; problem = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        try {
            LOGGER_WARN.warn("object: CustomerAgreement; stage: start; operation: delete; id = " + id);
            customerAgreementDao.delete(id);
            LOGGER_WARN.warn("object: CustomerAgreement; stage: finish; operation: delete; id = " + id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: CustomerAgreement; operation: delete; id = " + id + "; problem = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CustomerAgreement findById(String id) {
        try {
            return customerAgreementDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("object: Company; operation: findById; id = " + id + "; problem = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CustomerAgreement[] findAll() {
        return customerAgreementDao.findAll();
    }
}
