package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.NomenclatureRequestDto;
import ua.com.alevel.api.dto.response.entity.NomenclatureResponseDto;
import ua.com.alevel.facade.NomenclatureFacade;
import ua.com.alevel.persistence.entity.directory.Nomenclature;
import ua.com.alevel.service.BusinessDirectionService;
import ua.com.alevel.service.NomenclatureService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NomenclatureFacadeImpl implements NomenclatureFacade {

    private final NomenclatureService nomenclatureService;
    private final BusinessDirectionService businessDirectionService;

    public NomenclatureFacadeImpl(NomenclatureService nomenclatureService, BusinessDirectionService businessDirectionService) {
        this.nomenclatureService = nomenclatureService;
        this.businessDirectionService = businessDirectionService;
    }

    @Override
    public void create(NomenclatureRequestDto nomenclatureRequestDto) {
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setName(nomenclatureRequestDto.getName());
        nomenclature.setProduct(nomenclatureRequestDto.getProduct());
        nomenclature.setService(nomenclatureRequestDto.getService());
        nomenclature.setBusinessDirection(businessDirectionService.findById(nomenclatureRequestDto.getBusinessDirectionId()).get());
        nomenclatureService.create(nomenclature);
    }

    @Override
    public void update(NomenclatureRequestDto nomenclatureRequestDto, Long id) {
        Nomenclature nomenclature = nomenclatureService.findById(id).get();
        nomenclature.setUpdated(new Date());
        nomenclature.setName(nomenclatureRequestDto.getName());
        nomenclature.setProduct(nomenclatureRequestDto.getProduct());
        nomenclature.setService(nomenclatureRequestDto.getService());
        nomenclature.setBusinessDirection(businessDirectionService.findById(nomenclatureRequestDto.getBusinessDirectionId()).get());
        nomenclatureService.update(nomenclature);
    }

    @Override
    public void delete(Long id) {
        nomenclatureService.delete(id);
    }

    @Override
    public NomenclatureResponseDto findById(Long id) {
        return new NomenclatureResponseDto(nomenclatureService.findById(id).get());
    }

    @Override
    public List<NomenclatureResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Nomenclature> all = nomenclatureService.findAll(parameterMap);
        List<NomenclatureResponseDto> items = all.stream().map(NomenclatureResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return nomenclatureService.count(parameterMap);
    }
}
