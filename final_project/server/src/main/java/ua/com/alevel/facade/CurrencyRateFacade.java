package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.CurrencyRateRequestDto;
import ua.com.alevel.api.dto.response.table.CurrencyRateResponseDto;

import java.util.Date;

public interface CurrencyRateFacade extends BaseTableFacade<CurrencyRateRequestDto, CurrencyRateResponseDto> {
    CurrencyRateResponseDto findByDateAndAndCurrencyId(WebRequest request);
}
