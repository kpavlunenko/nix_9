package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CounterpartyRequestDto;
import ua.com.alevel.api.dto.response.CounterpartyResponseDto;
import ua.com.alevel.facade.CounterpartyFacade;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.service.CounterpartyService;


import java.util.List;
import java.util.Map;
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
    public long count() {
        return counterpartyService.count();
    }

    @Override
    public List<CounterpartyResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Counterparty> all = counterpartyService.findAll(parameterMap);
        List<CounterpartyResponseDto> items = all.stream().map(CounterpartyResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
