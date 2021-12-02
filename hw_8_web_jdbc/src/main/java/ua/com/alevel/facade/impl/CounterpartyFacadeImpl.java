package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CounterpartyFacade;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.service.CounterpartyService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.CounterpartyRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeDataRequestDto;
import ua.com.alevel.view.dto.request.SortDataRequestDto;
import ua.com.alevel.view.dto.response.CounterpartyResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounterpartyFacadeImpl implements CounterpartyFacade {

    private final CounterpartyService counterpartyService;

    public CounterpartyFacadeImpl(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @Override
    public void create(CounterpartyRequestDto counterpartyRequestDto) {
        Counterparty counterparty = new Counterparty();
        counterparty.setName(counterpartyRequestDto.getName());
        counterparty.setInn(counterpartyRequestDto.getInn());
        counterparty.setCounterpartyType(counterpartyRequestDto.getCounterpartyType());
        counterpartyService.create(counterparty);
    }

    @Override
    public void update(CounterpartyRequestDto counterpartyRequestDto, Long id) {
        Counterparty counterparty = counterpartyService.findById(id);
        counterparty.setName(counterpartyRequestDto.getName());
        counterparty.setInn(counterpartyRequestDto.getInn());
        counterparty.setCounterpartyType(counterpartyRequestDto.getCounterpartyType());
        counterpartyService.update(counterparty);
    }

    @Override
    public void delete(Long id) {
        counterpartyService.delete(id);
    }

    @Override
    public CounterpartyResponseDto findById(Long id) {
        return new CounterpartyResponseDto(counterpartyService.findById(id));
    }

    @Override
    public PageDataResponseDto<CounterpartyResponseDto> findAll(WebRequest request) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());
        requestDataTable.setFilters(WebRequestUtil.getFiltersFromRequest(request));

        ResponseDataTable<Counterparty> all = counterpartyService.findAll(requestDataTable);

        PageDataResponseDto<CounterpartyResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<CounterpartyResponseDto> items = all.getItems().stream()
                .map(CounterpartyResponseDto::new)
                .peek(CounterpartyResponseDto -> CounterpartyResponseDto.setCountOfAgreements((Long) all.getOtherParamMap().get(CounterpartyResponseDto.getId())))
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
    public PageDataResponseDto<CounterpartyResponseDto> findAllByCompany(WebRequest request, long companyId) {
        PageAndSizeDataRequestDto pageAndSizeDataRequestDto = WebRequestUtil.generatePageAndSizeData(request);
        SortDataRequestDto sortDataRequestDto = WebRequestUtil.generateSortData(request);

        RequestDataTable requestDataTable = new RequestDataTable();
        requestDataTable.setOrder(sortDataRequestDto.getOrder());
        requestDataTable.setSort(sortDataRequestDto.getSort());
        requestDataTable.setCurrentPage(pageAndSizeDataRequestDto.getPage());
        requestDataTable.setPageSize(pageAndSizeDataRequestDto.getSize());
        requestDataTable.setFilters(WebRequestUtil.getFiltersFromRequest(request));

        ResponseDataTable<Counterparty> all = counterpartyService.findAllByCompany(requestDataTable, companyId);

        PageDataResponseDto<CounterpartyResponseDto> pageDataResponseDto = new PageDataResponseDto<>();
        List<CounterpartyResponseDto> items = all.getItems().stream()
                .map(CounterpartyResponseDto::new)
                .peek(CounterpartyResponseDto -> CounterpartyResponseDto.setCountOfAgreements((Long) all.getOtherParamMap().get(CounterpartyResponseDto.getId())))
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
    public List<CounterpartyResponseDto> findAll() {
        List<Counterparty> all = counterpartyService.findAll();
        List<CounterpartyResponseDto> items = all.stream().map(CounterpartyResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
