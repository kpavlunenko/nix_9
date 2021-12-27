package ua.com.alevel.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.cron.model.CurrencyRatePrivatBank;
import ua.com.alevel.persistence.entity.Currency;
import ua.com.alevel.persistence.entity.CurrencyRate;
import ua.com.alevel.persistence.repository.CurrencyRateRepository;
import ua.com.alevel.persistence.repository.CurrencyRepository;
import ua.com.alevel.service.CurrencyRateAPIService;

import java.util.Date;
import java.util.Optional;

@Service
public class CurrencyRateAPIServiceImpl implements CurrencyRateAPIService {

    @Value("${currencyRate.url}")
    private String url;

    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public CurrencyRateAPIServiceImpl(CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    public void syncCurrencyRateToAPI() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<CurrencyRatePrivatBank[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                CurrencyRatePrivatBank[].class
        );

        if (response.getStatusCodeValue() == 200) {
            CurrencyRatePrivatBank[] currencyRatesAPI = response.getBody();
            if (currencyRatesAPI != null) {
                for (CurrencyRatePrivatBank currencyRateAPI : currencyRatesAPI) {
                    Optional<Currency> currencyOptional = currencyRepository.findByName(currencyRateAPI.getCcy());
                    if (currencyOptional.isPresent()) {
                        Currency currency = currencyOptional.get();
                        Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findByDateAndAndCurrency_Id(new Date(), currency.getId());
                        CurrencyRate currencyRate;
                        if (currencyRateOptional.isPresent()) {
                            currencyRate = currencyRateOptional.get();
                        } else  {
                            currencyRate = new CurrencyRate();
                        }
                        currencyRate.setCurrency(currency);
                        currencyRate.setFrequencyRate(1);
                        currencyRate.setRate(currencyRateAPI.getBuy());
                        currencyRate.setDate(new Date());
                        currencyRateRepository.save(currencyRate);
                    }
                }
            }
        }
    }
}
