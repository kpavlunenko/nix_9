package ua.com.alevel.persistence.entity;

import java.util.Set;

public class BusinessDirection extends BaseEntity {

    private String name;
    private Set<Company> companies;

    public BusinessDirection() {
        super();
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
