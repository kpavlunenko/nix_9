package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.BusinessDirectionRequestDto;
import ua.com.alevel.view.dto.response.BusinessDirectionResponseDto;
import ua.com.alevel.view.dto.response.CounterpartyResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

public interface BusinessDirectionFacade extends BaseFacade<BusinessDirectionRequestDto, BusinessDirectionResponseDto> {
    PageDataResponseDto<BusinessDirectionResponseDto> findAllByCompany(WebRequest request, long companyId);
}
