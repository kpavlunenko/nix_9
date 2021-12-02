package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.CompanyRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeDataRequestDto;
import ua.com.alevel.view.dto.request.SortDataRequestDto;
import ua.com.alevel.view.dto.response.AgreementResponseDto;
import ua.com.alevel.view.dto.response.CompanyResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.ArrayList;
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
    public PageDataResponseDto<CompanyResponseDto> findAll(WebRequest request) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());

        ResponseDataTable<Company> all = companyService.findAll(requestDataTable);

        PageDataResponseDto<CompanyResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<CompanyResponseDto> items = all.getItems().stream()
                .map(CompanyResponseDto::new)
                .peek(CompanyResponseDto -> CompanyResponseDto.setCountOfCounterparties((Long) all.getOtherParamMap().get(CompanyResponseDto.getId())))
                .collect(Collectors.toList());

        pageDataResponseDto.setItems(items);
        pageDataResponseDto.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        pageDataResponseDto.setPageSize(pageAndSizeDataRequestDto.getSize());
        pageDataResponseDto.setOrder(sortDataRequestDto.getOrder());
        pageDataResponseDto.setSort(sortDataRequestDto.getSort());
        pageDataResponseDto.setItemsSize(all.getItemsSize());
        pageDataResponseDto.setTotalPageSize(all.getTotalPageSize());
        pageDataResponseDto.setCurrentShowFromEntries(all.getEntriesFrom());
        pageDataResponseDto.setCurrentShowToEntries(all.getEntriesTo());
        pageDataResponseDto.initPaginationState(pageDataResponseDto.getCurrentPage());

        return pageDataResponseDto;
    }

    @Override
    public List<CompanyResponseDto> findAll() {
        List<Company> all = companyService.findAll();
        List<CompanyResponseDto> items = all.stream().map(CompanyResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
