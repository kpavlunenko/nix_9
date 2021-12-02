package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.CounterpartyRequestDto;
import ua.com.alevel.view.dto.response.CounterpartyResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

public interface CounterpartyFacade extends BaseFacade<CounterpartyRequestDto, CounterpartyResponseDto> {
    PageDataResponseDto<CounterpartyResponseDto> findAllByCompany(WebRequest request, long companyId);
}
