package ua.com.alevel.controller;

import ua.com.alevel.entity.Company;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.impl.CompanyServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompanyController {

    private final CompanyService companyService = new CompanyServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create company, please enter 1");
        System.out.println("if you want update company, please enter 2");
        System.out.println("if you want delete company, please enter 3");
        System.out.println("if you want findById company, please enter 4");
        System.out.println("if you want findAll company, please enter 5");
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
            System.out.println("Please, enter company name");
            String name = reader.readLine();
            Company company = new Company();
            company.setName(name);
            companyService.create(company);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter company name");
            String name = reader.readLine();
            Company company = new Company();
            company.setId(id);
            company.setName(name);
            companyService.update(company);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            companyService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Company company = companyService.findById(id);
            System.out.println("company = " + company);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        Company[] companies = companyService.findAll();
        boolean arrayIsEmpty = true;
        for (Company company : companies) {
            if (company != null) {
                arrayIsEmpty = false;
                System.out.println("company = " + company);
            }
        }
        if (arrayIsEmpty) {
            System.out.println("companies empty");
        }
    }
}