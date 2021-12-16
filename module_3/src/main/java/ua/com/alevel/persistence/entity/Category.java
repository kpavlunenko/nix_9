package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.BankOperationType;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_operation_type")
    private BankOperationType bankOperationType;

    public Category() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public void setBankOperationType(BankOperationType bankOperationType) {
        this.bankOperationType = bankOperationType;
    }
}
