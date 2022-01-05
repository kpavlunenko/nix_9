package ua.com.alevel.api.dto.request.entity;

import ua.com.alevel.api.dto.request.RequestDto;

import java.math.BigDecimal;

public class SalesInvoiceGoodRequestDto extends RequestDto {

    private long nomenclatureId;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal sum;

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
