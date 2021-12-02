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

import ua.com.alevel.facade.CounterpartyFacade;
import ua.com.alevel.type.CounterpartyType;
import ua.com.alevel.view.dto.request.CounterpartyRequestDto;
import ua.com.alevel.view.dto.response.CounterpartyResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/counterparties")
public class CounterpartyController extends BaseController {

    private final CounterpartyFacade counterpartyFacade;

    public CounterpartyController(CounterpartyFacade counterpartyFacade) {
        this.counterpartyFacade = counterpartyFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<CounterpartyResponseDto> response = counterpartyFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/counterparties/all");
        model.addAttribute("createNewItemUrl", "/counterparties/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", true);
        model.addAttribute("cardHeader", "All Counterparties");
        return "pages/counterparty/counterparty_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/counterparties", model);
    }

    @GetMapping("/all/company/{companyId}")
    public String findAllByCompany(@PathVariable Long companyId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<CounterpartyResponseDto> response = counterpartyFacade.findAllByCompany(request, companyId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/counterparties/all/company/" + companyId);
        model.addAttribute("createNewItemUrl", "/counterparties/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Counterparties");
        return "pages/counterparty/counterparty_all";
    }

    @PostMapping("/all/company/{companyId}")
    public ModelAndView findAllByCompanyRedirect(@PathVariable Long companyId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/counterparties/all/company/" + companyId, model);
    }

    @GetMapping("/new")
    public String redirectToNewCounterpartyPage(Model model) {
        model.addAttribute("counterparty", new CounterpartyRequestDto());
        model.addAttribute("counterpartyTypes", CounterpartyType.values());
        return "pages/counterparty/counterparty_new";
    }

    @PostMapping("/create")
    public String createNewCounterparty(RedirectAttributes attributes, @ModelAttribute("counterparty") CounterpartyRequestDto counterpartyRequestDto) {
        counterpartyFacade.create(counterpartyRequestDto);
        return "redirect:/counterparties";
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, @ModelAttribute("counterparty") CounterpartyRequestDto counterpartyRequestDto) {
        counterpartyFacade.update(counterpartyRequestDto, id);
        return "redirect:/counterparties";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        CounterpartyResponseDto counterpartyResponseDto = counterpartyFacade.findById(id);
        model.addAttribute("counterparty", counterpartyResponseDto);
        model.addAttribute("counterpartyTypes", CounterpartyType.values());
        return "pages/counterparty/counterparty_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        counterpartyFacade.delete(id);
        return "redirect:/counterparties";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        CounterpartyResponseDto counterpartyResponseDto = counterpartyFacade.findById(id);
        model.addAttribute("counterparty", counterpartyResponseDto);
        return "pages/counterparty/counterparty_details";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("name", "name", "counterparties.name"),
                new HeaderName("inn", "inn", "counterparties.inn"),
                new HeaderName("counterparty type", "counterpartyType", "counterparties.counterparty_type"),
                new HeaderName("agreements", null, null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnNames, PageDataResponseDto<CounterpartyResponseDto> response) {
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
