package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.Company;

public class CompanyShortResponseDto {

    private Long id;
    private String name;

    public CompanyShortResponseDto() {

    }

    public CompanyShortResponseDto(Company company) {
        setId(company.getId());
        this.name = company.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
