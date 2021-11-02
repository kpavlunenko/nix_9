package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CustomerDao;
import ua.com.alevel.dao.impl.CustomerDaoImpl;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CustomerAgreementService;
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
            customerDao.delete(id);
        }
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
