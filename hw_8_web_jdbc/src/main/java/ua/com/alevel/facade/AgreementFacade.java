package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.AgreementRequestDto;
import ua.com.alevel.view.dto.response.AgreementResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

public interface AgreementFacade extends BaseFacade<AgreementRequestDto, AgreementResponseDto> {
    PageDataResponseDto<AgreementResponseDto> findAllByCounterparty(WebRequest request, long counterpartyId);
}
