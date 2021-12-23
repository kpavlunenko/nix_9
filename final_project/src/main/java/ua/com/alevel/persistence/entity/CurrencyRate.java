package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "currency_rates")
public class CurrencyRate extends BaseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne
    private Currency currency;

    @Column(precision = 12, scale = 4)
    private BigDecimal rate;

    @Column(name = "frequency_rate")
    private Integer frequencyRate;

    public CurrencyRate() {
        super();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setFrequencyRate(Integer frequencyRate) {
        this.frequencyRate = frequencyRate;
    }

    public Integer getFrequencyRate() {
        return frequencyRate;
    }
}
