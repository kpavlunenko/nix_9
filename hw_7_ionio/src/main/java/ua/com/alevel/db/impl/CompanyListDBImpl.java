package ua.com.alevel.db.impl;

import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.CustomerAgreement;
import ua.com.alevel.util.GenerateIdUtil;
import ua.com.alevel.util.WriteReadDatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class CompanyListDBImpl implements CompanyDB {

    private final String PATH_OF_FILE_COMPANIES = "hw_7_ionio/companies.json";
    private List<Company> companies;
    private static CompanyListDBImpl instance;

    private CompanyListDBImpl() {
        companies = new ArrayList<>();
    }

    public static CompanyListDBImpl getInstance() {
        if (instance == null) {
            instance = new CompanyListDBImpl();
            WriteReadDatabaseUtil.readDatabase(instance);
        }
        return instance;
    }

    public void setDB(List<Company> companies) {
        this.companies = companies;
    }

    public Class getClassOfEntity() {
        return Company.class;
    }

    public String getPath() {
        return PATH_OF_FILE_COMPANIES;
    }

    public void create(Company company) {
        company.setId(GenerateIdUtil.generateId(companies));
        companies.add(company);
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public void update(Company company) {
        Company current = findById(company.getId());
        current.setName(company.getName());
        current.setId(company.getId());
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public void delete(String id) {
        companies.removeIf(company -> company.getId().equals(id));
        WriteReadDatabaseUtil.writeDatabase(instance);
    }

    public Company findById(String id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("company not found by id"));
    }

    public List<Company> findAll() {
        return companies;
    }
}
