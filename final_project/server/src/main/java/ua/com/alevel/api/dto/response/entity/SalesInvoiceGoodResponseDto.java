package ua.com.alevel.api.dto.response.entity;

import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;

import java.math.BigDecimal;

public class SalesInvoiceGoodResponseDto {

    private Long id;
    private NomenclatureResponseDto nomenclature;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal sum;

    public SalesInvoiceGoodResponseDto() {

    }

    public SalesInvoiceGoodResponseDto(SalesInvoiceGood salesInvoiceGood) {
        this.id = salesInvoiceGood.getId();
        this.nomenclature = new NomenclatureResponseDto(salesInvoiceGood.getNomenclature());
        this.price = salesInvoiceGood.getPrice();
        this.quantity = salesInvoiceGood.getQuantity();
        this.sum = salesInvoiceGood.getSum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
