package ua.com.alevel.service;

import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.impl.CompanyServiceImpl;
import ua.com.alevel.service.impl.CustomerAgreementServiceImpl;
import ua.com.alevel.service.impl.CustomerServiceImpl;

public class GenerationUtil {

    public static final String NAME_COMPANY = "test company";
    public static final String NAME_CUSTOMER = "test company";
    public static final String NAME_CUSTOMER_AGREEMENT = "test company";
    private final static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
    private final static CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private final static CustomerAgreementServiceImpl customerAgreementServiceImpl = new CustomerAgreementServiceImpl();

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

    public static void clearDB() {
        CustomerAgreement[] customerAgreements = customerAgreementServiceImpl.findAll();
        for (int i = 0; i < customerAgreements.length; i++) {
            customerAgreementServiceImpl.delete(customerAgreements[i].getId());
        }
        Company[] companies = companyServiceImpl.findAll();
        for (int i = 0; i < companies.length; i++) {
            companyServiceImpl.delete(companies[i].getId());
        }
        Customer[] customers = customerServiceImpl.findAll();
        for (int i = 0; i < customers.length; i++) {
            customerServiceImpl.delete(customers[i].getId());
        }
    }
}
