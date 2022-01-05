package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.SalesInvoiceGoodRequestDto;
import ua.com.alevel.api.dto.request.entity.SalesInvoiceRequestDto;
import ua.com.alevel.api.dto.response.entity.SalesInvoiceResponseDto;
import ua.com.alevel.facade.SalesInvoiceFacade;
import ua.com.alevel.persistence.entity.document.SalesInvoice;
import ua.com.alevel.persistence.entity.document.tabularpart.SalesInvoiceGood;
import ua.com.alevel.service.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesInvoiceFacadeImpl implements SalesInvoiceFacade {

    private final SalesInvoiceService salesInvoiceService;
    private final CompanyService companyService;
    private final CounterpartyService counterpartyService;
    private final AgreementService agreementService;
    private final CurrencyService currencyService;
    private final PriceTypeService priceTypeService;
    private final NomenclatureService nomenclatureService;

    public SalesInvoiceFacadeImpl(SalesInvoiceService salesInvoiceService, CompanyService companyService, CounterpartyService counterpartyService,
                                  AgreementService agreementService, CurrencyService currencyService, PriceTypeService priceTypeService,
                                  NomenclatureService nomenclatureService) {
        this.salesInvoiceService = salesInvoiceService;
        this.companyService = companyService;
        this.counterpartyService = counterpartyService;
        this.agreementService = agreementService;
        this.currencyService = currencyService;
        this.priceTypeService = priceTypeService;
        this.nomenclatureService = nomenclatureService;
    }

    @Override
    public void create(SalesInvoiceRequestDto salesInvoiceRequestDto) {
        SalesInvoice salesInvoice = initializeSalesInvoice(new SalesInvoice(), salesInvoiceRequestDto);
        salesInvoiceService.create(salesInvoice);
    }

    @Override
    public void update(SalesInvoiceRequestDto salesInvoiceRequestDto, Long id) {
        SalesInvoice salesInvoice = initializeSalesInvoice(salesInvoiceService.findById(id).get(), salesInvoiceRequestDto);
        salesInvoice.setUpdated(new Date());
        salesInvoiceService.update(salesInvoice);
    }

    @Override
    public void delete(Long id) {
        salesInvoiceService.delete(id);
    }

    @Override
    public SalesInvoiceResponseDto findById(Long id) {
        return new SalesInvoiceResponseDto(salesInvoiceService.findById(id).get());
    }

    @Override
    public List<SalesInvoiceResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<SalesInvoice> all = salesInvoiceService.findAll(parameterMap);
        List<SalesInvoiceResponseDto> items = all.stream().map(SalesInvoiceResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return salesInvoiceService.count(parameterMap);
    }

    private SalesInvoice initializeSalesInvoice(SalesInvoice salesInvoice, SalesInvoiceRequestDto salesInvoiceRequestDto) {
        salesInvoice.setDate(salesInvoiceRequestDto.getDate());
        salesInvoice.setCompany(companyService.findById(salesInvoiceRequestDto.getCompanyId()).get());
        salesInvoice.setCounterparty(counterpartyService.findById(salesInvoiceRequestDto.getCounterpartyId()).get());
        salesInvoice.setAgreement(agreementService.findById(salesInvoiceRequestDto.getAgreementId()).get());
        salesInvoice.setCurrency(currencyService.findById(salesInvoiceRequestDto.getCurrencyId()).get());
        salesInvoice.setPriceType(priceTypeService.findById(salesInvoiceRequestDto.getPriceTypeId()).get());

        List<SalesInvoiceGood> salesInvoiceGoods = new ArrayList<>();
        for (SalesInvoiceGoodRequestDto salesInvoiceGoodRequestDto : salesInvoiceRequestDto.getSalesInvoiceGoods()) {
            SalesInvoiceGood salesInvoiceGood = new SalesInvoiceGood();
            salesInvoiceGood.setNomenclature(nomenclatureService.findById(salesInvoiceGoodRequestDto.getNomenclatureId()).get());
            salesInvoiceGood.setPrice(salesInvoiceGoodRequestDto.getPrice());
            salesInvoiceGood.setQuantity(salesInvoiceGoodRequestDto.getQuantity());
            salesInvoiceGood.setSum(salesInvoiceGoodRequestDto.getSum());
            salesInvoiceGood.setSalesInvoice(salesInvoice);
            salesInvoiceGoods.add(salesInvoiceGood);
        }
        salesInvoice.setSalesInvoiceGoods(salesInvoiceGoods);

        return salesInvoice;
    }
}
