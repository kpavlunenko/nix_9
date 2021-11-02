package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.BaseController;
import ua.com.alevel.controller.DBController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DBControllerImpl implements DBController {

    private final Map<String, BaseController> entityInDB = new HashMap<>();

    public DBControllerImpl() {
        entityInDB.put("Company", new CompanyControllerImpl());
        entityInDB.put("Customer", new CustomerControllerImpl());
        entityInDB.put("CustomerAgreement", new CustomerAgreementControllerImpl());
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select an entity to work with");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
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
        System.out.println("if you want to work with companies, please enter 1");
        System.out.println("if you want to work with customers, please enter 2");
        System.out.println("if you want to work with customer agreements, please enter 3");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                entityInDB.get("Company").run();
                break;
            case "2":
                entityInDB.get("Customer").run();
                break;
            case "3":
                entityInDB.get("CustomerAgreement").run();
                break;
        }
        runNavigation();
    }
}
