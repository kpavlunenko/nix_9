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
}
