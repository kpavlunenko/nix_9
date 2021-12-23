package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.CurrencyRequestDto;
import ua.com.alevel.api.dto.response.entity.CurrencyResponseDto;
import ua.com.alevel.facade.CurrencyFacade;
import ua.com.alevel.persistence.entity.Currency;
import ua.com.alevel.service.CurrencyService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyFacadeImpl implements CurrencyFacade {

    private final CurrencyService currencyService;

    public CurrencyFacadeImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void create(CurrencyRequestDto currencyRequestDto) {
        Currency currency = new Currency();
        currency.setName(currencyRequestDto.getName());
        currency.setCode(currencyRequestDto.getCode());
        currencyService.create(currency);
    }

    @Override
    public void update(CurrencyRequestDto currencyRequestDto, Long id) {
        Currency currency = currencyService.findById(id).get();
        currency.setUpdated(new Date());
        currency.setName(currencyRequestDto.getName());
        currency.setCode(currencyRequestDto.getCode());
        currencyService.create(currency);
    }

    @Override
    public void delete(Long id) {
        currencyService.delete(id);
    }

    @Override
    public CurrencyResponseDto findById(Long id) {
        return new CurrencyResponseDto(currencyService.findById(id).get());
    }

    @Override
    public List<CurrencyResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Currency> all = currencyService.findAll(parameterMap);
        List<CurrencyResponseDto> items = all.stream().map(CurrencyResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return currencyService.count(parameterMap);
    }
}
