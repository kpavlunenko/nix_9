package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends AbstractTableRepository<CurrencyRate> {
}
