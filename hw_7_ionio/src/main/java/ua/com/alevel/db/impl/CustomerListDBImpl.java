package ua.com.alevel.db.impl;

import ua.com.alevel.db.CustomerDB;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.util.GenerateIdUtil;
import ua.com.alevel.util.WriteReadDatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerListDBImpl implements CustomerDB {

    private final String PATH_OF_FILE_CUSTOMERS = "hw_7_ionio/customers.json";
    private List<Customer> customers;
    private static CustomerListDBImpl instance;

    private CustomerListDBImpl() {
        customers = new ArrayList<>();
    }

    public static CustomerListDBImpl getInstance() {
        if (instance == null) {
            instance = new CustomerListDBImpl();
            WriteReadDatabaseUtil.readDatabase(instance);
        }
        return instance;
    }

    public void setDB(List<Customer> customers) {
        this.customers = customers;
    }

    public Class getClassOfEntity() {
        return Customer.class;
    }

    public String getPath() {
        return PATH_OF_FILE_CUSTOMERS;
    }

    public void create(Customer customer) {
        customer.setId(GenerateIdUtil.generateId(customers));
        customers.add(customer);
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public void update(Customer customer) {
        Customer current = findById(customer.getId());
        current.setId(customer.getId());
        current.setName(customer.getName());
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public void delete(String id) {
        customers.removeIf(customer -> customer.getId().equals(id));
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public Customer findById(String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("customer not found by id"));
    }

    public List<Customer> findAll() {
        return customers;
    }
}
