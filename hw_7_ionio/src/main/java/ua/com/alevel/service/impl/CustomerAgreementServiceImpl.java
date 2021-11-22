package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CustomerAgreementDao;
import ua.com.alevel.dao.impl.CustomerAgreementDaoImpl;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CustomerAgreementService;

import java.util.List;

public class CustomerAgreementServiceImpl implements CustomerAgreementService {

    private final CustomerAgreementDao customerAgreementDao = new CustomerAgreementDaoImpl();

    @Override
    public void create(CustomerAgreement entity) {
        customerAgreementDao.create(entity);
    }

    @Override
    public void update(CustomerAgreement entity) {
        try {
            customerAgreementDao.update(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        try {
            customerAgreementDao.delete(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public CustomerAgreement findById(String id) {
        try {
            return customerAgreementDao.findById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public List<CustomerAgreement> findAll() {
        return customerAgreementDao.findAll();
    }
}
