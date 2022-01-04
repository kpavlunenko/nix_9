package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.table.PriceRequestDto;
import ua.com.alevel.api.dto.response.table.PriceResponseDto;
import ua.com.alevel.facade.PriceFacade;
import ua.com.alevel.persistence.entity.Price;
import ua.com.alevel.service.NomenclatureService;
import ua.com.alevel.service.PriceService;
import ua.com.alevel.service.PriceTypeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceFacadeImpl implements PriceFacade {

    private final PriceService priceService;
    private final PriceTypeService priceTypeService;
    private final NomenclatureService nomenclatureService;

    public PriceFacadeImpl(PriceService priceService, PriceTypeService priceTypeService, NomenclatureService nomenclatureService) {
        this.priceService = priceService;
        this.priceTypeService = priceTypeService;
        this.nomenclatureService = nomenclatureService;
    }

    @Override
    public void create(PriceRequestDto priceRequestDto) {
        Price price = new Price();
        price.setDate(priceRequestDto.getDate());
        price.setPrice(priceRequestDto.getPrice());
        price.setPriceType(priceTypeService.findById(priceRequestDto.getPriceTypeId()).get());
        price.setNomenclature(nomenclatureService.findById(priceRequestDto.getNomenclatureId()).get());
        priceService.create(price);
    }

    @Override
    public void update(PriceRequestDto priceRequestDto, Long id) {
        Price price = priceService.findById(id).get();
        price.setDate(priceRequestDto.getDate());
        price.setPrice(priceRequestDto.getPrice());
        price.setPriceType(priceTypeService.findById(priceRequestDto.getPriceTypeId()).get());
        price.setNomenclature(nomenclatureService.findById(priceRequestDto.getNomenclatureId()).get());
        priceService.create(price);
    }

    @Override
    public void delete(Long id) {
        priceService.delete(id);
    }

    @Override
    public PriceResponseDto findById(Long id) {
        return new PriceResponseDto(priceService.findById(id).get());
    }

    @Override
    public List<PriceResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Price> all = priceService.findAll(parameterMap);
        List<PriceResponseDto> items = all.stream().map(PriceResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return priceService.count(parameterMap);
    }
}
