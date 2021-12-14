package ua.com.alevel.api.dto.request;

import java.util.List;

public class UserRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Long> bankAccountIds;

    public List<Long> getBankAccountIds() {
        return bankAccountIds;
    }

    public void setBankAccountIds(List<Long> bankAccountIds) {
        this.bankAccountIds = bankAccountIds;
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
