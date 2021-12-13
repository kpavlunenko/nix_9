package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.RequestDto;
import ua.com.alevel.api.dto.response.ResponseDto;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll(WebRequest request);
    long count(WebRequest request);
}
