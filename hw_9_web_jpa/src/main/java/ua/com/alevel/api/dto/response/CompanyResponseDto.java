package ua.com.alevel.api.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.type.CompanyType;

import java.util.stream.Collectors;

public class CompanyResponseDto extends ResponseDto {

    private String name;
    private CompanyType companyType;
    private BusinessDirectionShortResponseDto[] businessDirections;
//    private long countOfCounterparties;

    public CompanyResponseDto() {

    }

    public CompanyResponseDto(Company company) {
        setId(company.getId());
        setCreated(company.getCreated());
        setUpdated(company.getUpdated());
        setDeletionMark(company.isDeletionMark());
        this.name = company.getName();
        this.companyType = company.getCompanyType();
        if (CollectionUtils.isNotEmpty(company.getBusinessDirections())){
            this.businessDirections = company.getBusinessDirections()
                    .stream().map(BusinessDirectionShortResponseDto::new).collect(Collectors.toList()).toArray(new BusinessDirectionShortResponseDto[0]);
        } else {
            this.businessDirections = new BusinessDirectionShortResponseDto[0];
        }
    }

    public BusinessDirectionShortResponseDto[] getBusinessDirections() {
        return businessDirections;
    }

    public void setBusinessDirections(BusinessDirectionShortResponseDto[] businessDirections) {
        this.businessDirections = businessDirections;
    }

    //    public long getCountOfCounterparties() {
//        return countOfCounterparties;
//    }
//
//    public void setCountOfCounterparties(long countOfCounterparties) {
//        this.countOfCounterparties = countOfCounterparties;
//    }

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
