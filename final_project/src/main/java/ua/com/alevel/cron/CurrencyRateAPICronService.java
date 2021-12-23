package ua.com.alevel.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alevel.service.CurrencyRateAPIService;

@Service
public class CurrencyRateAPICronService {

    private final CurrencyRateAPIService currencyRateAPIService;

    public CurrencyRateAPICronService(CurrencyRateAPIService currencyRateAPIService) {
        this.currencyRateAPIService = currencyRateAPIService;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void syncCurrencyRateToAPI() {
        currencyRateAPIService.syncCurrencyRateToAPI();
    }
}
