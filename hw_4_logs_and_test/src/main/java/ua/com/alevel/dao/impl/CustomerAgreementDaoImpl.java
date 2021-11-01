package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ApplicationConfig;
import ua.com.alevel.dao.CustomerAgreementDao;
import ua.com.alevel.db.CustomerAgreementDB;
import ua.com.alevel.entity.CustomerAgreement;

public class CustomerAgreementDaoImpl implements CustomerAgreementDao {

    private final CustomerAgreementDB instanceDB = ApplicationConfig.getImpl(CustomerAgreementDB.class);

    @Override
    public void create(CustomerAgreement entity) {
        instanceDB.create(entity);
    }

    @Override
    public void update(CustomerAgreement entity) {
        instanceDB.update(entity);
    }

    @Override
    public void delete(String id) {
        instanceDB.delete(id);
    }

    @Override
    public CustomerAgreement findById(String id) {
        return instanceDB.findById(id);
    }

    @Override
    public CustomerAgreement[] findAll() {
        return instanceDB.findAll();
    }
}
