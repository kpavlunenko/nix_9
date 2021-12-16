package ua.com.alevel.api.dto.request;

import ua.com.alevel.persistence.type.BankOperationType;
import ua.com.alevel.persistence.type.OperationType;

import java.math.BigDecimal;

public class BankOperationRequestDto extends RequestDto {

    private BigDecimal amount;
    private long categoryId;
    private long bankAccountId;
    private long recipientBankAccountId;
    private OperationType operationType;
    private BankOperationType bankOperationType;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public long getRecipientBankAccountId() {
        return recipientBankAccountId;
    }

    public void setRecipientBankAccountId(long recipientBankAccountId) {
        this.recipientBankAccountId = recipientBankAccountId;
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
