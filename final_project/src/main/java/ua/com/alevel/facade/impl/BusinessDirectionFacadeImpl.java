package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.BusinessDirectionRequestDto;
import ua.com.alevel.api.dto.response.entity.BusinessDirectionResponseDto;
import ua.com.alevel.facade.BusinessDirectionFacade;
import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.service.BusinessDirectionService;
import ua.com.alevel.service.CompanyService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BusinessDirectionFacadeImpl implements BusinessDirectionFacade {

    private final BusinessDirectionService businessDirectionService;
    private final CompanyService companyService;

    public BusinessDirectionFacadeImpl(BusinessDirectionService businessDirectionService, CompanyService companyService) {
        this.businessDirectionService = businessDirectionService;
        this.companyService = companyService;
    }

    @Override
    public void create(BusinessDirectionRequestDto businessDirectionRequestDto) {
        BusinessDirection businessDirection = new BusinessDirection();
        businessDirection.setName(businessDirectionRequestDto.getName());
        businessDirection.setCompanies(businessDirectionRequestDto.getCompanyIds().stream()
                .filter(companyId -> companyId != null)
                .map(companyId -> companyService.findById(companyId).get())
                .collect(Collectors.toSet()));
        businessDirectionService.create(businessDirection);
    }

    @Override
    public void update(BusinessDirectionRequestDto businessDirectionRequestDto, Long id) {
        BusinessDirection businessDirection = businessDirectionService.findById(id).get();
        businessDirection.setUpdated(new Date());
        businessDirection.setName(businessDirectionRequestDto.getName());
        businessDirection.setCompanies(businessDirectionRequestDto.getCompanyIds().stream()
                .filter(companyId -> companyId != null)
                .map(companyId -> companyService.findById(companyId).get())
                .collect(Collectors.toSet()));
        businessDirectionService.update(businessDirection);
    }

    @Override
    public void delete(Long id) {
        businessDirectionService.delete(id);
    }

    @Override
    public BusinessDirectionResponseDto findById(Long id) {
        return new BusinessDirectionResponseDto(businessDirectionService.findById(id).get());
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return businessDirectionService.count(parameterMap);
    }

    @Override
    public List<BusinessDirectionResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<BusinessDirection> all = businessDirectionService.findAll(parameterMap);
        List<BusinessDirectionResponseDto> items = all.stream().map(BusinessDirectionResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
