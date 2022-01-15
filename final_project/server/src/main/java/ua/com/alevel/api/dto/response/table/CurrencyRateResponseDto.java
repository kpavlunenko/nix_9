package ua.com.alevel.api.dto.response.table;

import ua.com.alevel.api.dto.response.TableResponseDto;
import ua.com.alevel.api.dto.response.entity.CurrencyResponseDto;
import ua.com.alevel.persistence.entity.register.CurrencyRate;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyRateResponseDto extends TableResponseDto {

    private Long id;
    private Date date;
    private CurrencyResponseDto currency;
    private BigDecimal rate;
    private Integer frequencyRate;

    public CurrencyRateResponseDto() {

    }

    public CurrencyRateResponseDto(CurrencyRate currencyRate) {
        this.id = currencyRate.getId();
        this.date = currencyRate.getDate();
        this.rate = currencyRate.getRate();
        this.frequencyRate = currencyRate.getFrequencyRate();
        this.currency = new CurrencyResponseDto(currencyRate.getCurrency());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
