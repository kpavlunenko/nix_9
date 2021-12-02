package ua.com.alevel.persistence.entity;

import ua.com.alevel.type.CompanyType;

import java.util.Set;

public class Company extends BaseEntity {

    private String name;
    private CompanyType companyType;
    private Set<BusinessDirection> businessDirections;

    public Company() {
        super();
    }

    public Set<BusinessDirection> getBusinessDirections() {
        return businessDirections;
    }

    public void setBusinessDirections(Set<BusinessDirection> businessDirections) {
        this.businessDirections = businessDirections;
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
