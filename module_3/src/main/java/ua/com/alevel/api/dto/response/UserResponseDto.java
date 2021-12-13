package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.User;

public class UserResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

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
