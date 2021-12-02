package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BusinessDirectionFacade;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.persistence.entity.Company;
import ua.com.alevel.service.BusinessDirectionService;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.BusinessDirectionRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeDataRequestDto;
import ua.com.alevel.view.dto.request.SortDataRequestDto;
import ua.com.alevel.view.dto.response.BusinessDirectionResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<Company> companies = new HashSet<>();
        for (Long companyId:businessDirectionRequestDto.getCompaniesIds()) {
            if (companyId != 0) {
                companies.add(companyService.findById(companyId));
            }
        }
        businessDirection.setCompanies(companies);
        businessDirectionService.create(businessDirection);
    }

    @Override
    public void update(BusinessDirectionRequestDto businessDirectionRequestDto, Long id) {
        BusinessDirection businessDirection = businessDirectionService.findById(id);
        businessDirection.setName(businessDirectionRequestDto.getName());
        Set<Company> companies = new HashSet<>();
        for (Long companyId:businessDirectionRequestDto.getCompaniesIds()) {
            if (companyId != 0) {
                companies.add(companyService.findById(companyId));
            }
        }
        businessDirection.setCompanies(companies);
        businessDirectionService.update(businessDirection);
    }

    @Override
    public void delete(Long id) {
        businessDirectionService.delete(id);
    }

    @Override
    public BusinessDirectionResponseDto findById(Long id) {
        return new BusinessDirectionResponseDto(businessDirectionService.findById(id));
    }

    @Override
    public PageDataResponseDto<BusinessDirectionResponseDto> findAll(WebRequest request) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());

        ResponseDataTable<BusinessDirection> all = businessDirectionService.findAll(requestDataTable);

        PageDataResponseDto<BusinessDirectionResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<BusinessDirectionResponseDto> items = all.getItems().stream()
                .map(BusinessDirectionResponseDto::new)
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
    public PageDataResponseDto<BusinessDirectionResponseDto> findAllByCompany(WebRequest request, long companyId) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());
        requestDataTable.setFilters(WebRequestUtil.getFiltersFromRequest(request));

        ResponseDataTable<BusinessDirection> all = businessDirectionService.findAllByCompany(requestDataTable, companyId);

        PageDataResponseDto<BusinessDirectionResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<BusinessDirectionResponseDto> items = all.getItems().stream()
                .map(BusinessDirectionResponseDto::new)
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
    public List<BusinessDirectionResponseDto> findAll() {
        List<BusinessDirection> all = businessDirectionService.findAll();
        List<BusinessDirectionResponseDto> items = all.stream().map(BusinessDirectionResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
