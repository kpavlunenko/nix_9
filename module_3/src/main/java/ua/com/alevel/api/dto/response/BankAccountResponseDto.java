package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.BankAccount;

public class BankAccountResponseDto extends ResponseDto {

    private String name;
    private String iban;
    private UserShortResponseDto user;

    public BankAccountResponseDto() {

    }

    public BankAccountResponseDto(BankAccount bankAccount) {
        setId(bankAccount.getId());
        setCreated(bankAccount.getCreated());
        setUpdated(bankAccount.getUpdated());
        setDeletionMark(bankAccount.isDeletionMark());
        setName(bankAccount.getName());
        setIban(bankAccount.getIban());
        setUser(new UserShortResponseDto(bankAccount.getUser()));
    }

    public UserShortResponseDto getUser() {
        return user;
    }

    public void setUser(UserShortResponseDto userShortResponseDto) {
        this.user = userShortResponseDto;
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
