package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.register.CurrencyRate;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends AbstractTableRepository<CurrencyRate> {
    Optional<CurrencyRate> findByDateAndAndCurrency_Id(Date date, Long id);
    void deleteAllByCurrency_Id(Long id);
    @Query(value = "select * from currency_rates where currency_id = :currencyId and date <= :date order by date desc limit 1", nativeQuery = true)
    Optional<CurrencyRate> findActualRateByDateAndAndCurrency_Id(@Param("currencyId") long currency, @Param("date") Date date);
}
