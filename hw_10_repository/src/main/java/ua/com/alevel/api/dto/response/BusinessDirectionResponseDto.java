package ua.com.alevel.api.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.BusinessDirection;

import java.util.stream.Collectors;

public class BusinessDirectionResponseDto extends ResponseDto {

    private String name;
    private CompanyShortResponseDto[] companies;

    public BusinessDirectionResponseDto(BusinessDirection businessDirection) {
        setId(businessDirection.getId());
        setCreated(businessDirection.getCreated());
        setUpdated(businessDirection.getUpdated());
        setDeletionMark(businessDirection.isDeletionMark());
        this.name = businessDirection.getName();
        if (CollectionUtils.isNotEmpty(businessDirection.getCompanies())){
            this.companies = businessDirection.getCompanies()
                    .stream().map(CompanyShortResponseDto::new).collect(Collectors.toList()).toArray(new CompanyShortResponseDto[0]);
        } else {
            this.companies = new CompanyShortResponseDto[0];
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyShortResponseDto[] getCompanies() {
        return companies;
    }

    public void setCompanies(CompanyShortResponseDto[] companies) {
        this.companies = companies;
    }
}
