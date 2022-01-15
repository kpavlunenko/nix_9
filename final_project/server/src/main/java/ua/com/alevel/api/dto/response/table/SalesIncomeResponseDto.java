package ua.com.alevel.api.dto.response.table;

import ua.com.alevel.api.dto.response.TableResponseDto;
import ua.com.alevel.persistence.entity.register.SalesIncome;

import java.math.BigDecimal;
import java.util.Date;

public class SalesIncomeResponseDto extends TableResponseDto {

    private Date date;
    private double revenue;
    private double profit;

    public SalesIncomeResponseDto() {

    }

    public SalesIncomeResponseDto(SalesIncome salesIncome) {
        this.date = salesIncome.getDate();
        this.revenue = salesIncome.getRevenue().doubleValue();
        this.profit = salesIncome.getProfit().doubleValue();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
