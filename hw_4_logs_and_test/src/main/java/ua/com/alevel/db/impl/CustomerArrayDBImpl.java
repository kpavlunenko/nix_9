package ua.com.alevel.db.impl;

import ua.com.alevel.db.CustomerDB;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.util.GenerateIdUtil;

import java.util.Arrays;

public class CustomerArrayDBImpl implements CustomerDB {

    private Customer [] customers = new Customer[2];
    private static CustomerArrayDBImpl instance;
    private static int countOfItems = 0;

    private CustomerArrayDBImpl() {
        customers = new Customer[2];
    }

    public static CustomerArrayDBImpl getInstance() {
        if (instance == null) {
            instance = new CustomerArrayDBImpl();
        }
        return instance;
    }

    public void create (Customer customer) {
        customer.setId(GenerateIdUtil.generateId(customers, countOfItems));
        customers[countOfItems] = customer;
        countOfItems++;
        if(countOfItems == customers.length) {
            customers = Arrays.copyOf(customers, countOfItems + 10);
        }
    }

    public void update(Customer customer) {
        customers[findIndexCustomerInArray(customer.getId())] = customer;
    }

    public void delete(String id) {
        int idInArray = findIndexCustomerInArray(id);
        customers[idInArray] = null;
        for (int i = idInArray; i < countOfItems - 1; i++) {
            customers[i] = customers[i + 1];
        }
        customers[countOfItems - 1] = null;
        countOfItems--;
    }

    public Customer findById(String id) {
        for (int i = 0; i < countOfItems; i++) {
            if (customers[i].getId().equals(id)) {
                return customers[i];
            }
        }
        throw new RuntimeException("user not found by id");
    }

    public Customer [] findAll() {
        return customers;
    }

    private int findIndexCustomerInArray(String id) {
        for (int i = 0; i < countOfItems; i++) {
            if (customers[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("user not found by id");
    }
}
