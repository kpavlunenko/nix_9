package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.BusinessDirection;

public class BusinessDirectionShortResponseDto {

    private Long id;
    private String name;

    public BusinessDirectionShortResponseDto() {

    }

    public BusinessDirectionShortResponseDto(BusinessDirection businessDirection) {
        setId(businessDirection.getId());
        this.name = businessDirection.getName();
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
}
