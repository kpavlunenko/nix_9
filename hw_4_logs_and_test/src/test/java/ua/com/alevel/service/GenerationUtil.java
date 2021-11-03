package ua.com.alevel.service;

import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;

public class GenerationUtil {

    public static final String NAME_COMPANY = "test company";
    public static final String NAME_CUSTOMER = "test company";
    public static final String NAME_CUSTOMER_AGREEMENT = "test company";

    public static Company generateCompany(String name) {
        Company company = new Company();
        company.setName(name);
        return company;
    }

    public static Customer generateCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return customer;
    }

    public static CustomerAgreement generateCustomerAgreement(String name, Company company, Customer customer) {
        CustomerAgreement customerAgreement = new CustomerAgreement();
        customerAgreement.setName(name);
        customerAgreement.setCompany(company);
        customerAgreement.setCustomer(customer);
        return customerAgreement;
    }

}
