package ua.com.alevel.api.dto.response.table;

import ua.com.alevel.api.dto.response.TableResponseDto;
import ua.com.alevel.api.dto.response.entity.NomenclatureResponseDto;
import ua.com.alevel.api.dto.response.entity.PriceTypeResponseDto;
import ua.com.alevel.persistence.entity.register.Price;

import java.math.BigDecimal;

public class PriceResponseDto extends TableResponseDto {

    private PriceTypeResponseDto priceType;
    private NomenclatureResponseDto nomenclature;
    private BigDecimal price;

    public PriceResponseDto() {

    }

    public PriceResponseDto(Price price) {
        setId(price.getId());
        setDate(price.getDate());
        this.price = price.getPrice();
        this.priceType = new PriceTypeResponseDto(price.getPriceType());
        this.nomenclature = new NomenclatureResponseDto(price.getNomenclature());
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
