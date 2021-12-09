package ua.com.alevel.api.dto.request;

import java.util.List;

public class BusinessDirectionRequestDto extends RequestDto {

    private String name;
    private List<Long> companyIds;

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
