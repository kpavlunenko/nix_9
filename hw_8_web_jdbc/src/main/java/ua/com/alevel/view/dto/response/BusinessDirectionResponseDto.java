package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.persistence.entity.Company;

import java.util.List;
import java.util.Set;

public class BusinessDirectionResponseDto extends ResponseDto {

    private String name;
    private Set<Company> companies;
    private List<Long> companiesIds;

    public BusinessDirectionResponseDto(BusinessDirection businessDirection) {
        setId(businessDirection.getId());
        setCreated(businessDirection.getCreated());
        setUpdated(businessDirection.getUpdated());
        setDeletionMark(businessDirection.isDeletionMark());
        this.name = businessDirection.getName();
        this.companies = businessDirection.getCompanies();
    }

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
