package ua.com.alevel.persistence.entity;

import ua.com.alevel.type.CompanyType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type")
    private CompanyType companyType;

    @ManyToMany
    @JoinTable(
            name = "company_business_direction",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "business_direction_id"))
    private Set<BusinessDirection> businessDirections;

    public Company() {
        super();
    }

    public Set<BusinessDirection> getBusinessDirections() {
        return businessDirections;
    }

    public void setBusinessDirections(Set<BusinessDirection> businessDirections) {
        this.businessDirections = businessDirections;
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
