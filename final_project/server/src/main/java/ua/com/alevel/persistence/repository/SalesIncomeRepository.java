package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.register.SalesIncome;

@Repository
public interface SalesIncomeRepository extends AbstractTableRepository<SalesIncome> {
    void deleteAllBySalesInvoice_Id(Long id);
}
