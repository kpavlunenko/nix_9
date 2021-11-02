package ua.com.alevel.service;

import ua.com.alevel.entity.Company;

public class CompanyGenerationUtil {

    public static final String NAME = "test company";

    public static Company generateCompany(String name) {
        Company company = new Company();
        company.setName(name);
        return company;
    }

}
