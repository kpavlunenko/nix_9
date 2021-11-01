package ua.com.alevel.db.impl;

import ua.com.alevel.db.CustomerAgreementDB;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.util.GenerateIdUtil;

import java.util.Arrays;

public class CustomerAgreementArrayDBImpl implements CustomerAgreementDB {

    private CustomerAgreement[] customerAgreements = new CustomerAgreement[2];
    private static CustomerAgreementArrayDBImpl instance;
    private static int countOfItems = 0;

    private CustomerAgreementArrayDBImpl() {
        customerAgreements = new CustomerAgreement[2];
    }

    public static CustomerAgreementArrayDBImpl getInstance() {
        if (instance == null) {
            instance = new CustomerAgreementArrayDBImpl();
        }
        return instance;
    }

    public void create (CustomerAgreement customerAgreement) {
        customerAgreement.setId(GenerateIdUtil.generateId(customerAgreements, countOfItems));
        customerAgreements[countOfItems] = customerAgreement;
        countOfItems++;
        if(countOfItems == customerAgreements.length) {
            customerAgreements = Arrays.copyOf(customerAgreements, countOfItems + 10);
        }
    }

    public void update(CustomerAgreement customerAgreement) {
        customerAgreements[findIndexCustomerAgreementInArray(customerAgreement.getId())] = customerAgreement;
    }

    public void delete(String id) {
        int idInArray = findIndexCustomerAgreementInArray(id);
        customerAgreements[idInArray] = null;
        for (int i = idInArray; i < countOfItems - 1; i++) {
            customerAgreements[i] = customerAgreements[i + 1];
        }
        customerAgreements[countOfItems - 1] = null;
        countOfItems--;
    }

    public CustomerAgreement findById(String id) {
        for (int i = 0; i < countOfItems; i++) {
            if (customerAgreements[i].getId().equals(id)) {
                return customerAgreements[i];
            }
        }
        throw new RuntimeException("user not found by id");
    }

    public CustomerAgreement[] findAll() {
        return customerAgreements;
    }

    private int findIndexCustomerAgreementInArray(String id) {
        for (int i = 0; i < countOfItems; i++) {
            if (customerAgreements[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("user not found by id");
    }
}
