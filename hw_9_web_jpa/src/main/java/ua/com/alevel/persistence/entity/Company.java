package ua.com.alevel.persistence.entity;

import ua.com.alevel.type.CompanyType;

public class Company extends BaseEntity {

    private String name;
    private CompanyType companyType;

    public Company() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }
}
