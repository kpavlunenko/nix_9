package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.CustomerController;
import ua.com.alevel.entity.Customer;
import ua.com.alevel.service.CustomerService;
import ua.com.alevel.service.impl.CustomerServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService = new CustomerServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-------------------");
        System.out.println("Entity Customer");
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
        System.out.println("if you want create customer, please enter 1");
        System.out.println("if you want update customer, please enter 2");
        System.out.println("if you want delete customer, please enter 3");
        System.out.println("if you want findById customer, please enter 4");
        System.out.println("if you want findAll customer, please enter 5");
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
            System.out.println("Please, enter customer name");
            String name = reader.readLine();
            Customer customer = new Customer();
            customer.setName(name);
            customerService.create(customer);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter customer name");
            String name = reader.readLine();
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            customerService.update(customer);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            customerService.delete(id);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Customer customer = customerService.findById(id);
            System.out.println("customer = " + customer);
        } catch (IOException|RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        List<Customer> customers = customerService.findAll();
        if (customers.size() != 0) {
            for (Customer customer : customers) {
                System.out.println("customer = " + customer);
            }
        } else {
            System.out.println("customers empty");
        }
    }
}