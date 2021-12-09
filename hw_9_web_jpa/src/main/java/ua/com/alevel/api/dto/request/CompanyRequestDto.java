package ua.com.alevel.api.dto.request;

import ua.com.alevel.type.CompanyType;

import java.util.List;

public class CompanyRequestDto extends RequestDto {

    private String name;
    private CompanyType companyType;
    private List<Long> businessDirectionIds;

    public List<Long> getBusinessDirectionIds() {
        return businessDirectionIds;
    }

    public void setBusinessDirectionIds(List<Long> businessDirectionIds) {
        this.businessDirectionIds = businessDirectionIds;
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
