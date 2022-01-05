package ua.com.alevel.persistence.entity.directory;

import ua.com.alevel.persistence.entity.BaseTable;
import ua.com.alevel.persistence.entity.register.PriceType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "prices")
public class Price extends BaseTable {

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne
    private PriceType priceType;

    @OneToOne
    private Nomenclature nomenclature;

    @Column(precision = 12, scale = 4)
    private BigDecimal price;

    public Price() {
        super();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
