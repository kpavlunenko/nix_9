package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.AgreementRequestDto;
import ua.com.alevel.api.dto.response.AgreementResponseDto;
import ua.com.alevel.facade.AgreementFacade;
import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CounterpartyService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AgreementFacadeImpl implements AgreementFacade {

    private final AgreementService agreementService;
    private final CompanyService companyService;
    private final CounterpartyService counterpartyService;

    public AgreementFacadeImpl(AgreementService agreementService, CompanyService companyService, CounterpartyService counterpartyService) {
        this.agreementService = agreementService;
        this.companyService = companyService;
        this.counterpartyService = counterpartyService;
    }

    @Override
    public void create(AgreementRequestDto agreementRequestDto) {
        Agreement agreement = new Agreement();
        agreement.setName(agreementRequestDto.getName());
        agreement.setAgreementType(agreementRequestDto.getAgreementType());
        agreement.setCompany(companyService.findById(agreementRequestDto.getCompanyId()));
        agreement.setCounterparty(counterpartyService.findById(agreementRequestDto.getCounterpartyId()));
        agreementService.create(agreement);
    }

    @Override
    public void update(AgreementRequestDto agreementRequestDto, Long id) {
        Agreement agreement = agreementService.findById(id);
        agreement.setUpdated(new Date());
        agreement.setName(agreementRequestDto.getName());
        agreement.setAgreementType(agreementRequestDto.getAgreementType());
        agreement.setCompany(companyService.findById(agreementRequestDto.getCompanyId()));
        agreement.setCounterparty(counterpartyService.findById(agreementRequestDto.getCounterpartyId()));
        agreementService.update(agreement);
    }

    @Override
    public void delete(Long id) {
        agreementService.delete(id);
    }

    @Override
    public AgreementResponseDto findById(Long id) {
        return new AgreementResponseDto(agreementService.findById(id));
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return agreementService.count(parameterMap);
    }

    @Override
    public List<AgreementResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Agreement> all = agreementService.findAll(parameterMap);
        List<AgreementResponseDto> items = all.stream().map(AgreementResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
