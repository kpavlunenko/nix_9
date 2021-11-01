package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.dao.impl.CustomerDaoImpl;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void create(Customer entity) {
        customerDao.create(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public void delete(String id) {
        customerDao.delete(id);
    }

    @Override
    public Customer findById(String id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer[] findAll() {
        return customerDao.findAll();
    }
}
