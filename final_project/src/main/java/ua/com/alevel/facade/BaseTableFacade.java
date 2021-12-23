package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.TableRequestDto;
import ua.com.alevel.api.dto.response.TableResponseDto;

import java.util.List;

public interface BaseTableFacade <REQ extends TableRequestDto, RES extends TableResponseDto> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll(WebRequest request);
    long count(WebRequest request);
}
