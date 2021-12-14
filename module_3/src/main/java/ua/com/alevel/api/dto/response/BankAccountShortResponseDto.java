package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.BankAccount;

public class BankAccountShortResponseDto {

    private Long id;
    private String name;
    private String iban;

    public BankAccountShortResponseDto() {

    }

    public BankAccountShortResponseDto(BankAccount bankAccount) {
        setId(bankAccount.getId());
        setName(bankAccount.getName());
        setIban(bankAccount.getIban());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
