package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.register.CurrencyRate;

import java.util.Date;
import java.util.Optional;

public interface CurrencyRateService extends BaseTableService<CurrencyRate> {
    Optional<CurrencyRate> findByDateAndAndCurrencyId(Date date, Long id);
}
