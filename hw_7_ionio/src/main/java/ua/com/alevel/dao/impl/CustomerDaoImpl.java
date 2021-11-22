package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.db.CustomerDB;
import ua.com.alevel.db.impl.CustomerListDBImpl;
import ua.com.alevel.entity.Customer;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private final CustomerDB instanceDB = CustomerListDBImpl.getInstance();

    @Override
    public void create(Customer entity) {
        instanceDB.create(entity);
    }

    @Override
    public void update(Customer entity) {
        instanceDB.update(entity);
    }

    @Override
    public void delete(String id) {
        instanceDB.delete(id);
    }

    @Override
    public Customer findById(String id) {
        return instanceDB.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return instanceDB.findAll();
    }
}
