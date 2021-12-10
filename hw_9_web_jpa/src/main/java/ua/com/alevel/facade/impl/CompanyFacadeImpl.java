package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.service.BusinessDirectionService;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.api.dto.request.CompanyRequestDto;
import ua.com.alevel.api.dto.response.CompanyResponseDto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompanyFacadeImpl implements CompanyFacade {

    private final BusinessDirectionService businessDirectionService;
    private final CompanyService companyService;

    public CompanyFacadeImpl(CompanyService companyService, BusinessDirectionService businessDirectionService) {
        this.companyService = companyService;
        this.businessDirectionService = businessDirectionService;
    }

    @Override
    public void create(CompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setName(companyRequestDto.getName());
        company.setCompanyType(companyRequestDto.getCompanyType());
        company.setBusinessDirections(companyRequestDto.getBusinessDirectionIds().stream()
                .filter(businessDirectionId -> businessDirectionId != null)
                .map(businessDirectionId -> businessDirectionService.findById(businessDirectionId))
                .collect(Collectors.toSet()));
        companyService.create(company);
    }

    @Override
    public void update(CompanyRequestDto companyRequestDto, Long id) {
        Company company = companyService.findById(id);
        company.setUpdated(new Date());
        company.setName(companyRequestDto.getName());
        company.setCompanyType(companyRequestDto.getCompanyType());
        company.setBusinessDirections(companyRequestDto.getBusinessDirectionIds().stream()
                .filter(businessDirectionId -> businessDirectionId != null)
                .map(businessDirectionId -> businessDirectionService.findById(businessDirectionId))
                .collect(Collectors.toSet()));
        companyService.update(company);
    }

    @Override
    public void delete(Long id) {
        companyService.delete(id);
    }

    @Override
    public CompanyResponseDto findById(Long id) {
        return new CompanyResponseDto(companyService.findById(id));
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return companyService.count(parameterMap);
    }

    @Override
    public List<CompanyResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Company> all = companyService.findAll(parameterMap);
        List<CompanyResponseDto> items = all.stream().map(CompanyResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
