package ua.com.alevel.api.dto.request.table;

import ua.com.alevel.api.dto.request.TableRequestDto;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyRateRequestDto extends TableRequestDto {

    private long currencyId;
    private BigDecimal rate;
    private Integer frequencyRate;
    private Date date;

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
