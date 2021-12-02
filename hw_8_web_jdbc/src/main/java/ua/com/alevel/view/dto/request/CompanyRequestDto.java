package ua.com.alevel.view.dto.request;

import ua.com.alevel.type.CompanyType;

public class CompanyRequestDto extends RequestDto {

    private String name;
    private CompanyType companyType;

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
