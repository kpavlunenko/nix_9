package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.api.dto.request.CompanyRequestDto;
import ua.com.alevel.api.dto.response.CompanyResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyFacadeImpl implements CompanyFacade {

    private final CompanyService companyService;

    public CompanyFacadeImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void create(CompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setName(companyRequestDto.getName());
        company.setCompanyType(companyRequestDto.getCompanyType());
        companyService.create(company);
    }

    @Override
    public void update(CompanyRequestDto companyRequestDto, Long id) {
        Company company = companyService.findById(id);
        company.setName(companyRequestDto.getName());
        company.setCompanyType(companyRequestDto.getCompanyType());
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
    public List<CompanyResponseDto> findAll() {
        List<Company> all = companyService.findAll();
        List<CompanyResponseDto> items = all.stream().map(CompanyResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
