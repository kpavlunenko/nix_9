package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.CustomerAgreementController;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CustomerAgreementService;
import ua.com.alevel.service.CustomerService;
import ua.com.alevel.service.impl.CompanyServiceImpl;
import ua.com.alevel.service.impl.CustomerAgreementServiceImpl;
import ua.com.alevel.service.impl.CustomerServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CustomerAgreementControllerImpl implements CustomerAgreementController {

    private final CustomerAgreementService customerAgreementService = new CustomerAgreementServiceImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final CompanyService companyService = new CompanyServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-------------------");
        System.out.println("Entity Customer agreement");
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create customer agreement, please enter 1");
        System.out.println("if you want update customer agreement, please enter 2");
        System.out.println("if you want delete customer agreement, please enter 3");
        System.out.println("if you want findById customer agreement, please enter 4");
        System.out.println("if you want findAll customer agreements, please enter 5");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll();
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter customer agreement name");
            String name = reader.readLine();
            CustomerAgreement customerAgreement = new CustomerAgreement();
            customerAgreement.setName(name);
            System.out.println("Please, enter id of customer");
            String customerId = reader.readLine();
            customerAgreement.setCustomer(customerService.findById(customerId));
            System.out.println("Please, enter id of company");
            String companyId = reader.readLine();
            customerAgreement.setCompany(companyService.findById(companyId));
            customerAgreementService.create(customerAgreement);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter customer agreement name");
            String name = reader.readLine();
            CustomerAgreement customerAgreement = new CustomerAgreement();
            customerAgreement.setId(id);
            customerAgreement.setName(name);
            System.out.println("Please, enter id of customer");
            String customerId = reader.readLine();
            customerAgreement.setCustomer(customerService.findById(customerId));
            System.out.println("Please, enter id of company");
            String companyId = reader.readLine();
            customerAgreement.setCompany(companyService.findById(companyId));
            customerAgreementService.update(customerAgreement);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            customerAgreementService.delete(id);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            CustomerAgreement customerAgreement = customerAgreementService.findById(id);
            System.out.println("Customer agreement = " + customerAgreement);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        List<CustomerAgreement> customerAgreements = customerAgreementService.findAll();
        if (customerAgreements.size() != 0) {
            for (CustomerAgreement customerAgreement : customerAgreements) {
                System.out.println("Customer agreement = " + customerAgreement);
            }
        } else {
            System.out.println("Customer agreements empty");
        }
    }
}
