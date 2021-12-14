package ua.com.alevel.api.dto.request;

import ua.com.alevel.persistence.type.BankOperationType;

public class CategoryRequestDto extends RequestDto {

    private String name;
    private BankOperationType bankOperationType;

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
