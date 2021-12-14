package ua.com.alevel.api.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.User;

import java.util.stream.Collectors;

public class UserResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BankAccountShortResponseDto[] bankAccounts;

    public UserResponseDto() {

    }

    public UserResponseDto(User user) {
        setId(user.getId());
        setCreated(user.getCreated());
        setUpdated(user.getUpdated());
        setDeletionMark(user.isDeletionMark());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        if (CollectionUtils.isNotEmpty(user.getBankAccounts())){
            this.bankAccounts = user.getBankAccounts()
                    .stream().map(BankAccountShortResponseDto::new).collect(Collectors.toList()).toArray(new BankAccountShortResponseDto[0]);
        } else {
            this.bankAccounts = new BankAccountShortResponseDto[0];
        }
    }

    public BankAccountShortResponseDto[] getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccountShortResponseDto[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
