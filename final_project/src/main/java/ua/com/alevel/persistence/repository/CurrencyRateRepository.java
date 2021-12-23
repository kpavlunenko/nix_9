package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.CurrencyRate;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends AbstractTableRepository<CurrencyRate> {
    Optional<CurrencyRate> findByDateAndAndCurrency_Id(Date date, Long id);
}
