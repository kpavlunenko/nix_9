package ua.com.alevel.persistence.entity.directory;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    private String name;
    private String code;

    public Currency() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
