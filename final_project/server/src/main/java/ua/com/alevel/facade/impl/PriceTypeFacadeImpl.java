package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.PriceTypeRequestDto;
import ua.com.alevel.api.dto.response.entity.PriceTypeResponseDto;
import ua.com.alevel.facade.PriceTypeFacade;
import ua.com.alevel.persistence.entity.PriceType;
import ua.com.alevel.service.PriceTypeService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceTypeFacadeImpl implements PriceTypeFacade {

    private final PriceTypeService priceTypeService;

    public PriceTypeFacadeImpl(PriceTypeService priceTypeService) {
        this.priceTypeService = priceTypeService;
    }

    @Override
    public void create(PriceTypeRequestDto priceTypeRequestDto) {
        PriceType priceType = new PriceType();
        priceType.setName(priceTypeRequestDto.getName());
        priceTypeService.create(priceType);
    }

    @Override
    public void update(PriceTypeRequestDto priceTypeRequestDto, Long id) {
        PriceType priceType = priceTypeService.findById(id).get();
        priceType.setUpdated(new Date());
        priceType.setName(priceTypeRequestDto.getName());
        priceTypeService.update(priceType);
    }

    @Override
    public void delete(Long id) {
        priceTypeService.delete(id);
    }

    @Override
    public PriceTypeResponseDto findById(Long id) {
        return new PriceTypeResponseDto(priceTypeService.findById(id).get());
    }

    @Override
    public List<PriceTypeResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<PriceType> all = priceTypeService.findAll(parameterMap);
        List<PriceTypeResponseDto> items = all.stream().map(PriceTypeResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return priceTypeService.count(parameterMap);
    }
}
