package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.dao.impl.CustomerDaoImpl;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CustomerAgreementService;
import ua.com.alevel.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void create(Customer entity) {
        customerDao.create(entity);
    }

    @Override
    public void update(Customer entity) {
        try {
            customerDao.update(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        CustomerAgreementService customerAgreementService = new CustomerAgreementServiceImpl();
        List<CustomerAgreement> customerAgreements = customerAgreementService.findAll();
        boolean allowToDelete = true;
            if (customerAgreements.stream().anyMatch(customerAgreement -> customerAgreement.getCustomer().getId().equals(id))) {
                if(allowToDelete){
                    System.out.println("For current company exist customer agreements, operation 'delete' isn't allowed!");
                    System.out.println("You need to delete next agreements:");
                    allowToDelete = false;
                }
                customerAgreements.stream()
                        .filter(customerAgreement -> customerAgreement.getCustomer().getId().equals(id))
                        .forEach(customerAgreement -> System.out.println(customerAgreement.getId()));
            }
        if(allowToDelete) {
            try {
                customerDao.delete(id);
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }

    @Override
    public Customer findById(String id) {
        try {
            return customerDao.findById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
