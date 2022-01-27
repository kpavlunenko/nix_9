package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.CurrencyRateRequestDto;
import ua.com.alevel.api.dto.response.table.CurrencyRateResponseDto;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.facade.CurrencyRateFacade;
import ua.com.alevel.persistence.entity.register.CurrencyRate;
import ua.com.alevel.service.CurrencyRateService;
import ua.com.alevel.service.CurrencyService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyRateFacadeImpl implements CurrencyRateFacade {

    private final CurrencyRateService currencyRateService;
    private final CurrencyService currencyService;

    public CurrencyRateFacadeImpl(CurrencyRateService currencyRateService, CurrencyService currencyService) {
        this.currencyRateService = currencyRateService;
        this.currencyService = currencyService;
    }

    @Override
    public void create(CurrencyRateRequestDto currencyRateRequestDto) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setDate(currencyRateRequestDto.getDate());
        currencyRate.setRate(currencyRateRequestDto.getRate());
        currencyRate.setFrequencyRate(currencyRateRequestDto.getFrequencyRate());
        currencyRate.setCurrency(currencyService.findById(currencyRateRequestDto.getCurrencyId()).get());
        currencyRateService.create(currencyRate);
    }

    @Override
    public void update(CurrencyRateRequestDto currencyRateRequestDto, Long id) {
        CurrencyRate currencyRate = currencyRateService.findById(id).get();
        currencyRate.setDate(currencyRateRequestDto.getDate());
        currencyRate.setRate(currencyRateRequestDto.getRate());
        currencyRate.setFrequencyRate(currencyRateRequestDto.getFrequencyRate());
        currencyRate.setCurrency(currencyService.findById(currencyRateRequestDto.getCurrencyId()).get());
        currencyRateService.update(currencyRate);
    }

    @Override
    public void delete(Long id) {
        currencyRateService.delete(id);
    }

    @Override
    public CurrencyRateResponseDto findById(Long id) {
        return new CurrencyRateResponseDto(currencyRateService.findById(id).get());
    }

    @Override
    public List<CurrencyRateResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<CurrencyRate> all = currencyRateService.findAll(parameterMap);
        List<CurrencyRateResponseDto> items = all.stream().map(CurrencyRateResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return currencyRateService.count(parameterMap);
    }

    @Override
    public CurrencyRateResponseDto findByDateAndAndCurrencyId(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.get("currency") != null && parameterMap.get("date") != null) {
            Long currencyId = 0L;
            Date date = new Date();
            try {
                currencyId = Long.parseLong(parameterMap.get("currency")[0]);
                date = new Date(Long.parseLong(parameterMap.get("date")[0]));
            } catch (IllegalArgumentException e) {
                throw new IncorrectInputData("parameters for query is wrong");
            }
            return new CurrencyRateResponseDto(currencyRateService.findByDateAndAndCurrencyId(date, currencyId).get());
        } else {
            throw new IncorrectInputData("parameters for query is wrong");
        }
    }
}
