package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Company;

import java.util.List;
import java.util.Set;

public class BusinessDirectionRequestDto extends RequestDto {

    private String name;
    private Set<Company> companies;
    private List<Long> companiesIds;

    public List<Long> getCompaniesIds() {
        return companiesIds;
    }

    public void setCompaniesIds(List<Long> companiesIds) {
        this.companiesIds = companiesIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
}
