package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.com.alevel.facade.AgreementFacade;
import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.facade.CounterpartyFacade;
import ua.com.alevel.type.AgreementType;
import ua.com.alevel.view.dto.request.AgreementRequestDto;
import ua.com.alevel.view.dto.response.AgreementResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/agreements")
public class AgreementController extends BaseController {

    private final AgreementFacade agreementFacade;
    private final CompanyFacade companyFacade;
    private final CounterpartyFacade counterpartyFacade;

    public AgreementController(AgreementFacade agreementFacade, CompanyFacade companyFacade, CounterpartyFacade counterpartyFacade) {
        this.agreementFacade = agreementFacade;
        this.companyFacade = companyFacade;
        this.counterpartyFacade = counterpartyFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<AgreementResponseDto> response = agreementFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/agreements/all");
        model.addAttribute("createNewItemUrl", "/agreements/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", true);
        model.addAttribute("cardHeader", "All Agreements");
        return "pages/agreement/agreement_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/agreements", model);
    }

    @GetMapping("/all/counterparty/{counterpartyId}")
    public String findAll(@PathVariable Long counterpartyId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<AgreementResponseDto> response = agreementFacade.findAllByCounterparty(request, counterpartyId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/agreements/all/counterparty/" + counterpartyId);
        model.addAttribute("createNewItemUrl", "/agreements/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Agreements");
        return "pages/agreement/agreement_all";
    }

    @PostMapping("/all/counterparty/{counterpartyId}")
    public ModelAndView findAllByCompanyRedirect(@PathVariable Long counterpartyId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/agreements/all/counterparty/" + counterpartyId, model);
    }

    @GetMapping("/new")
    public String redirectToNewAgreementPage(Model model) {
        model.addAttribute("agreement", new AgreementRequestDto());
        model.addAttribute("agreementTypes", AgreementType.values());
        model.addAttribute("companies", companyFacade.findAll());
        model.addAttribute("counterparties", counterpartyFacade.findAll());
        return "pages/agreement/agreement_new";
    }

    @PostMapping("/create")
    public String createNewAgreement(RedirectAttributes attributes, @ModelAttribute("agreement") AgreementRequestDto agreementRequestDto) {
        agreementFacade.create(agreementRequestDto);
        return "redirect:/agreements";
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, @ModelAttribute("agreement") AgreementRequestDto agreementRequestDto) {
        agreementFacade.update(agreementRequestDto, id);
        return "redirect:/agreements";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        AgreementResponseDto agreementResponseDto = agreementFacade.findById(id);
        model.addAttribute("agreement", agreementResponseDto);
        model.addAttribute("agreementTypes", AgreementType.values());
        model.addAttribute("companies", companyFacade.findAll());
        model.addAttribute("counterparties", counterpartyFacade.findAll());
        return "pages/agreement/agreement_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        agreementFacade.delete(id);
        return "redirect:/agreements";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        AgreementResponseDto agreementResponseDto = agreementFacade.findById(id);
        model.addAttribute("agreement", agreementResponseDto);
        return "pages/agreement/agreement_details";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("name", "name", "agreement.name"),
                new HeaderName("agreement type", "agreementType", "agreement.agreement_type"),
                new HeaderName("company", "company", "company.name"),
                new HeaderName("counterparty", "counterparty", "counterparty.name"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnNames, PageDataResponseDto<AgreementResponseDto> response) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getDbName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        return headerDataList;
    }
}
