package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.BankOperationType;
import ua.com.alevel.persistence.type.OperationType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bank_operations")
public class BankOperation extends BaseEntity {

    private BigDecimal amount;

    @OneToOne
    private Category category;

    @OneToOne
    private BankAccount bankAccount;

    @OneToOne
    private BankAccount recipientBankAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_operation_type")
    private BankOperationType bankOperationType;

    public BankOperation() {
        super();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getRecipientBankAccount() {
        return recipientBankAccount;
    }

    public void setRecipientBankAccount(BankAccount recipientBankAccount) {
        this.recipientBankAccount = recipientBankAccount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public void setBankOperationType(BankOperationType bankOperationType) {
        this.bankOperationType = bankOperationType;
    }
}
