package ua.com.alevel.api.dto.response.table;

import ua.com.alevel.api.dto.response.TableResponseDto;
import ua.com.alevel.api.dto.response.entity.CurrencyResponseDto;
import ua.com.alevel.persistence.entity.CurrencyRate;

import java.math.BigDecimal;

public class CurrencyRateResponseDto extends TableResponseDto {

    private CurrencyResponseDto currency;
    private BigDecimal rate;
    private Integer frequencyRate;

    public CurrencyRateResponseDto() {

    }

    public CurrencyRateResponseDto(CurrencyRate currencyRate) {
        setId(currencyRate.getId());
        setDate(currencyRate.getDate());
        this.rate = currencyRate.getRate();
        this.frequencyRate = currencyRate.getFrequencyRate();
        this.currency = new CurrencyResponseDto(currencyRate.getCurrency());
    }

    public CurrencyResponseDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyResponseDto currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getFrequencyRate() {
        return frequencyRate;
    }

    public void setFrequencyRate(Integer frequencyRate) {
        this.frequencyRate = frequencyRate;
    }
}
