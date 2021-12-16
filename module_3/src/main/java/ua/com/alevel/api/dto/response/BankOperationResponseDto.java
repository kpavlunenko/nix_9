package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.BankOperation;
import ua.com.alevel.persistence.type.BankOperationType;
import ua.com.alevel.persistence.type.OperationType;

import java.math.BigDecimal;

public class BankOperationResponseDto extends ResponseDto {

    private BigDecimal amount;
    private CategoryResponseDto category;
    private BankAccountShortResponseDto bankAccount;
    private BankAccountShortResponseDto recipientBankAccount;
    private OperationType operationType;
    private BankOperationType bankOperationType;

    public BankOperationResponseDto() {

    }

    public BankOperationResponseDto(BankOperation bankOperation) {
        setId(bankOperation.getId());
        setCreated(bankOperation.getCreated());
        setUpdated(bankOperation.getUpdated());
        setDeletionMark(bankOperation.isDeletionMark());
        setAmount(bankOperation.getAmount());
        setCategory(new CategoryResponseDto(bankOperation.getCategory()));
        setBankAccount(new BankAccountShortResponseDto(bankOperation.getBankAccount()));
        setRecipientBankAccount(new BankAccountShortResponseDto(bankOperation.getRecipientBankAccount()));
        setOperationType(bankOperation.getOperationType());
        setBankOperationType(bankOperation.getBankOperationType());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CategoryResponseDto getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDto category) {
        this.category = category;
    }

    public BankAccountShortResponseDto getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountShortResponseDto bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccountShortResponseDto getRecipientBankAccount() {
        return recipientBankAccount;
    }

    public void setRecipientBankAccount(BankAccountShortResponseDto recipientBankAccount) {
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
