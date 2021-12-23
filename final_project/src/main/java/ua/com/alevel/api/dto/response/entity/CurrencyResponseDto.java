package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.api.dto.response.ResponseDto;
import ua.com.alevel.persistence.entity.Currency;

public class CurrencyResponseDto extends ResponseDto {

    private String name;
    private String code;

    public CurrencyResponseDto() {

    }

    public CurrencyResponseDto(Currency currency) {
        setId(currency.getId());
        setCreated(currency.getCreated());
        setUpdated(currency.getUpdated());
        setDeletionMark(currency.isDeletionMark());
        this.name = currency.getName();
        this.code = currency.getCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
