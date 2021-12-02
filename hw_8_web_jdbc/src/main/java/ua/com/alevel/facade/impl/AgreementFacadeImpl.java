package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.AgreementFacade;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.service.AgreementService;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.CounterpartyService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.AgreementRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeDataRequestDto;
import ua.com.alevel.view.dto.request.SortDataRequestDto;
import ua.com.alevel.view.dto.response.AgreementResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.List;
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
    public PageDataResponseDto<AgreementResponseDto> findAll(WebRequest request) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);
        if (sortDataRequestDto.getSort().lastIndexOf(".") == -1) {
            sortDataRequestDto.setSort("agreement." + sortDataRequestDto.getSort());
        }

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());

        ResponseDataTable<Agreement> all = agreementService.findAll(requestDataTable);

        PageDataResponseDto<AgreementResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<AgreementResponseDto> items = all.getItems().stream().map(AgreementResponseDto::new).collect(Collectors.toList());
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
    public PageDataResponseDto<AgreementResponseDto> findAllByCounterparty(WebRequest request, long counterpartyId) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);
        if (sortDataRequestDto.getSort().lastIndexOf(".") == -1) {
            sortDataRequestDto.setSort("agreement." + sortDataRequestDto.getSort());
        }

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());

        ResponseDataTable<Agreement> all = agreementService.findAllByCounterparty(requestDataTable, counterpartyId);

        PageDataResponseDto<AgreementResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<AgreementResponseDto> items = all.getItems().stream().map(AgreementResponseDto::new).collect(Collectors.toList());
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
    public List<AgreementResponseDto> findAll() {
        List<Agreement> all = agreementService.findAll();
        List<AgreementResponseDto> items = all.stream().map(AgreementResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
