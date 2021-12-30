package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.type.RoleType;

import java.util.Date;

public class PersonalResponseDto extends ResponseDto {

    private String email;
    private Boolean enabled;
    private RoleType roleType;
    private Date birthDay;
    private String firstName;
    private String lastName;

    public PersonalResponseDto() {

    }

    public PersonalResponseDto(Personal personal) {
        setId(personal.getId());
        setCreated(personal.getCreated());
        setUpdated(personal.getUpdated());
        setDeletionMark(personal.isDeletionMark());
        this.email = personal.getEmail();
        this.enabled = personal.getEnabled();
        this.roleType = personal.getRoleType();
        this.birthDay = personal.getBirthDay();
        this.firstName = personal.getFirstName();
        this.lastName = personal.getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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
