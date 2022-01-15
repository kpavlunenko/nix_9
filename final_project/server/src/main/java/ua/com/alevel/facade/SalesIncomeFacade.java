package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.SalesIncomeRequestDto;
import ua.com.alevel.api.dto.response.table.SalesIncomeResponseDto;

import java.util.List;

public interface SalesIncomeFacade extends BaseTableFacade<SalesIncomeRequestDto, SalesIncomeResponseDto> {
    List<SalesIncomeResponseDto> getSalesIncomeByPeriod(WebRequest request);
}
