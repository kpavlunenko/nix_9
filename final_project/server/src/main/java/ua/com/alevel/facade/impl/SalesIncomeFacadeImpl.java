package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.SalesIncomeRequestDto;
import ua.com.alevel.api.dto.response.table.SalesIncomeResponseDto;
import ua.com.alevel.facade.SalesIncomeFacade;
import ua.com.alevel.persistence.entity.register.SalesIncome;
import ua.com.alevel.service.SalesIncomeService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesIncomeFacadeImpl implements SalesIncomeFacade {

    private final SalesIncomeService salesIncomeService;

    public SalesIncomeFacadeImpl(SalesIncomeService salesIncomeService) {
        this.salesIncomeService = salesIncomeService;
    }

    @Override
    public void create(SalesIncomeRequestDto salesIncomeRequestDto) {
    }

    @Override
    public void update(SalesIncomeRequestDto salesIncomeRequestDto, Long id) {
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public SalesIncomeResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<SalesIncomeResponseDto> findAll(WebRequest request) {
        return null;
    }

    @Override
    public long count(WebRequest request) {
        return 0;
    }

    @Override
    public List<SalesIncomeResponseDto> getSalesIncomeByPeriod(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<SalesIncome> all = salesIncomeService.getSalesIncomeByPeriod(parameterMap);
        List<SalesIncomeResponseDto> items = all.stream().map(SalesIncomeResponseDto::new).collect(Collectors.toList());
        Map<Date, List<SalesIncomeResponseDto>> dates = items.stream().collect(
                Collectors.groupingBy(SalesIncomeResponseDto::getDate));
        items.clear();
        dates.forEach((key, value) -> {
            SalesIncomeResponseDto incomeResponseDto = new SalesIncomeResponseDto();
            incomeResponseDto.setDate(key);
            incomeResponseDto.setRevenue(value.stream().collect(Collectors.summingDouble(SalesIncomeResponseDto::getRevenue)));
            incomeResponseDto.setProfit(value.stream().collect(Collectors.summingDouble(SalesIncomeResponseDto::getProfit)));
            items.add(incomeResponseDto);
        });
        items.sort(Comparator.comparing(o -> o.getDate()));
        return items;
    }
}
