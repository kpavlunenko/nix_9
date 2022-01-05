package ua.com.alevel.persistence.entity.directory;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nomenclatures")
public class Nomenclature extends BaseEntity {

    private String name;
    private Boolean product;
    private Boolean service;

    @OneToOne
    private BusinessDirection businessDirection;

    public Nomenclature() {
        super();
    }

    public String getName() {
        return name;
    }

    public BusinessDirection getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(BusinessDirection businessDirection) {
        this.businessDirection = businessDirection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProduct() {
        return product;
    }

    public void setProduct(Boolean product) {
        this.product = product;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }
}
