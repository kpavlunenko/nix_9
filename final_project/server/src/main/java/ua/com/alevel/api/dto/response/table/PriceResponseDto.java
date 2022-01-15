package ua.com.alevel.api.dto.response.table;

import ua.com.alevel.api.dto.response.TableResponseDto;
import ua.com.alevel.api.dto.response.entity.NomenclatureResponseDto;
import ua.com.alevel.api.dto.response.entity.PriceTypeResponseDto;
import ua.com.alevel.persistence.entity.register.Price;

import java.math.BigDecimal;
import java.util.Date;

public class PriceResponseDto extends TableResponseDto {

    private Long id;
    private Date date;
    private PriceTypeResponseDto priceType;
    private NomenclatureResponseDto nomenclature;
    private BigDecimal price;

    public PriceResponseDto() {

    }

    public PriceResponseDto(Price price) {
        this.id = price.getId();
        this.date = price.getDate();
        this.price = price.getPrice();
        this.priceType = new PriceTypeResponseDto(price.getPriceType());
        this.nomenclature = new NomenclatureResponseDto(price.getNomenclature());
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

    public PriceTypeResponseDto getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceTypeResponseDto priceType) {
        this.priceType = priceType;
    }

    public NomenclatureResponseDto getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(NomenclatureResponseDto nomenclature) {
        this.nomenclature = nomenclature;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
