package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.User;

public class UserShortResponseDto {

    private Long id;
    private String firstName;
    private String lastName;

    public UserShortResponseDto() {

    }

    public UserShortResponseDto(User user) {
        setId(user.getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
