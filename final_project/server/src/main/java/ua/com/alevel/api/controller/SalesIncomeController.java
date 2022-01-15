package ua.com.alevel.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.response.table.SalesIncomeResponseDto;
import ua.com.alevel.facade.SalesIncomeFacade;

import java.util.List;

@RestController
@RequestMapping("/api/sales_incomes")
public class SalesIncomeController {

    private final SalesIncomeFacade salesIncomeFacade;

    public SalesIncomeController(SalesIncomeFacade salesIncomeFacade) {
        this.salesIncomeFacade = salesIncomeFacade;
    }

    @GetMapping
    public ResponseEntity<List<SalesIncomeResponseDto>> getSalesIncomeByPeriod(WebRequest request) {
        return ResponseEntity.ok(salesIncomeFacade.getSalesIncomeByPeriod(request));
    }
}
