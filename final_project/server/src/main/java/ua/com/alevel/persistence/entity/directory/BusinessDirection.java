package ua.com.alevel.persistence.entity.directory;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "business_directions")
public class BusinessDirection extends BaseEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "company_business_direction",
            joinColumns = @JoinColumn(name = "business_direction_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies;

    public BusinessDirection() {
        super();
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
