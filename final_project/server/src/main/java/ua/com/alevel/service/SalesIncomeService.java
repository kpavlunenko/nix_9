package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.register.SalesIncome;

public interface SalesIncomeService extends BaseTableService<SalesIncome> {
    void deleteBySalesInvoice(Long id);
}
