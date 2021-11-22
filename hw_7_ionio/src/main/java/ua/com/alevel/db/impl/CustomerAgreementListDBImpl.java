package ua.com.alevel.db.impl;

import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.db.CustomerAgreementDB;
import ua.com.alevel.db.CustomerDB;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.util.GenerateIdUtil;
import ua.com.alevel.util.WriteReadDatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerAgreementListDBImpl implements CustomerAgreementDB {

    private final String PATH_OF_FILE_CUSTOMER_AGREEMENTS = "hw_7_ionio/customer_agreements.json";
    private List<CustomerAgreement> customerAgreements;
    private static CustomerAgreementListDBImpl instance;

    private CustomerAgreementListDBImpl() {
        customerAgreements = new ArrayList<>();
    }

    public static CustomerAgreementListDBImpl getInstance() {
        if (instance == null) {
            instance = new CustomerAgreementListDBImpl();
            WriteReadDatabaseUtil.readDatabase(instance);
        }
        return instance;
    }

    public void setDB(List<CustomerAgreement> customerAgreements) {
        this.customerAgreements = customerAgreements;
        CompanyDB companyDB = CompanyListDBImpl.getInstance();
        CustomerDB customerDB = CustomerListDBImpl.getInstance();
        for (CustomerAgreement customerAgreement:customerAgreements) {
            customerAgreement.setCompany(companyDB.findById(customerAgreement.getCompany().getId()));
            customerAgreement.setCustomer(customerDB.findById(customerAgreement.getCustomer().getId()));
        }
    }

    public Class getClassOfEntity() {
        return CustomerAgreement.class;
    }

    public String getPath() {
        return PATH_OF_FILE_CUSTOMER_AGREEMENTS;
    }

    public void create(CustomerAgreement customerAgreement) {
        customerAgreement.setId(GenerateIdUtil.generateId(customerAgreements));
        customerAgreements.add(customerAgreement);
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public void update(CustomerAgreement customerAgreement) {
        CustomerAgreement current = findById(customerAgreement.getId());
        current.setId(customerAgreement.getId());
        current.setName(customerAgreement.getName());
        current.setCustomer(customerAgreement.getCustomer());
        current.setCompany(customerAgreement.getCompany());
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public void delete(String id) {
        customerAgreements.removeIf(customerAgreement -> customerAgreement.getId().equals(id));
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public CustomerAgreement findById(String id) {
        return customerAgreements.stream()
                .filter(customerAgreement -> customerAgreement.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("customer agreement not found by id"));
    }

    public List<CustomerAgreement> findAll() {
        return customerAgreements;
    }
}
