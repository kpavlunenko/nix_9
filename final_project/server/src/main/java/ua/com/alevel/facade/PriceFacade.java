package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.PriceRequestDto;
import ua.com.alevel.api.dto.response.table.PriceResponseDto;

public interface PriceFacade extends BaseTableFacade<PriceRequestDto, PriceResponseDto> {
    PriceResponseDto getNomenclaturePrice(WebRequest request);
}
