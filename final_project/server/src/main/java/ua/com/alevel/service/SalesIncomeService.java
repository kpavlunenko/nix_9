package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.register.SalesIncome;

import java.util.List;
import java.util.Map;

public interface SalesIncomeService extends BaseTableService<SalesIncome> {
    void deleteBySalesInvoice(Long id);
    List<SalesIncome> getSalesIncomeByPeriod(Map<String, String[]> parameterMap);
}
