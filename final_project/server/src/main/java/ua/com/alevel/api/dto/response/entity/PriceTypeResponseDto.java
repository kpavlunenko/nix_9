package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.PriceType;

public class PriceTypeResponseDto extends ResponseDto {

    private String name;

    public PriceTypeResponseDto() {

    }

    public PriceTypeResponseDto(PriceType priceType) {
        setId(priceType.getId());
        setCreated(priceType.getCreated());
        setUpdated(priceType.getUpdated());
        setDeletionMark(priceType.isDeletionMark());
        this.name = priceType.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
