package ua.com.alevel.persistence.entity.register;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "price_types")
public class PriceType extends BaseEntity {

    private String name;

    public PriceType() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
