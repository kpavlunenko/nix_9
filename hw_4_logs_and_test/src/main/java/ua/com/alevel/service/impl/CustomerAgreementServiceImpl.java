package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CustomerAgreementDao;
import ua.com.alevel.dao.impl.CustomerAgreementDaoImpl;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CustomerAgreementService;

public class CustomerAgreementServiceImpl implements CustomerAgreementService {

    private final CustomerAgreementDao customerAgreementDao = new CustomerAgreementDaoImpl();

    @Override
    public void create(CustomerAgreement entity) {
        customerAgreementDao.create(entity);
    }

    @Override
    public void update(CustomerAgreement entity) {
        customerAgreementDao.update(entity);
    }

    @Override
    public void delete(String id) {
        customerAgreementDao.delete(id);
    }

    @Override
    public CustomerAgreement findById(String id) {
        return customerAgreementDao.findById(id);
    }

    @Override
    public CustomerAgreement[] findAll() {
        return customerAgreementDao.findAll();
    }
}
