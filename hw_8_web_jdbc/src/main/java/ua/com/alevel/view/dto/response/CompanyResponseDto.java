package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.type.CompanyType;

public class CompanyResponseDto extends ResponseDto {

    private String name;
    private CompanyType companyType;
    private long countOfCounterparties;

    public CompanyResponseDto() {

    }

    public CompanyResponseDto(Company company) {
        setId(company.getId());
        setCreated(company.getCreated());
        setUpdated(company.getUpdated());
        setDeletionMark(company.isDeletionMark());
        this.name = company.getName();
        this.companyType = company.getCompanyType();
    }

    public long getCountOfCounterparties() {
        return countOfCounterparties;
    }

    public void setCountOfCounterparties(long countOfCounterparties) {
        this.countOfCounterparties = countOfCounterparties;
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
