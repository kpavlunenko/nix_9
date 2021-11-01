package ua.com.alevel.db.impl;

import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.entity.Company;
import ua.com.alevel.util.GenerateIdUtil;

import java.util.Arrays;

public class CompanyArrayDBImpl implements CompanyDB {

    private Company [] companies = new Company[2];
    private static CompanyArrayDBImpl instance;
    private static int countOfItems = 0;

    private CompanyArrayDBImpl() {
        companies = new Company[2];
    }

    public static CompanyArrayDBImpl getInstance() {
        if (instance == null) {
            instance = new CompanyArrayDBImpl();
        }
        return instance;
    }

    public void create (Company company) {
        company.setId(GenerateIdUtil.generateId(companies, countOfItems));
        companies[countOfItems] = company;
        countOfItems++;
        if(countOfItems == companies.length) {
            companies = Arrays.copyOf(companies, countOfItems + 10);
        }
    }

    public void update(Company company) {
        companies[findIndexCompanyInArray(company.getId())] = company;
    }

    public void delete(String id) {
        int idInArray = findIndexCompanyInArray(id);
        companies[idInArray] = null;
        for (int i = idInArray; i < countOfItems - 1; i++) {
            companies[i] = companies[i + 1];
        }
        companies[countOfItems - 1] = null;
        countOfItems--;
    }

    public Company findById(String id) {
        for (int i = 0; i < countOfItems; i++) {
            if (companies[i].getId().equals(id)) {
                return companies[i];
            }
        }
        throw new RuntimeException("user not found by id");
    }

    public Company [] findAll() {
        return companies;
    }

    private int findIndexCompanyInArray(String id) {
        for (int i = 0; i < countOfItems; i++) {
            if (companies[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("user not found by id");
    }
}
