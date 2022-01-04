package ua.com.alevel.api.dto.request.table;

import ua.com.alevel.api.dto.request.TableRequestDto;

import java.math.BigDecimal;
import java.util.Date;

public class PriceRequestDto extends TableRequestDto {

    private long priceTypeId;
    private long nomenclatureId;
    private BigDecimal price;
    private Date date;

    public long getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(long priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public long getNomenclatureId() {
        return nomenclatureId;
    }

    public void setNomenclatureId(long nomenclatureId) {
        this.nomenclatureId = nomenclatureId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
