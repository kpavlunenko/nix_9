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

import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.type.CompanyType;
import ua.com.alevel.view.dto.request.CompanyRequestDto;
import ua.com.alevel.view.dto.response.CompanyResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/companies")
public class CompanyController extends BaseController {

    private final CompanyFacade companyFacade;

    public CompanyController(CompanyFacade companyFacade) {
        this.companyFacade = companyFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<CompanyResponseDto> response = companyFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/companies/all");
        model.addAttribute("createNewItemUrl", "/companies/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", true);
        model.addAttribute("cardHeader", "All Companies");
        return "pages/company/company_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/companies", model);
    }

    @GetMapping("/new")
    public String redirectToNewCompanyPage(Model model) {
        model.addAttribute("company", new CompanyRequestDto());
        model.addAttribute("companyTypes", CompanyType.values());
        return "pages/company/company_new";
    }

    @PostMapping("/create")
    public String createNewCompany(RedirectAttributes attributes, @ModelAttribute("company") CompanyRequestDto companyRequestDto) {
        companyFacade.create(companyRequestDto);
        return "redirect:/companies";
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, @ModelAttribute("company") CompanyRequestDto companyRequestDto) {
        companyFacade.update(companyRequestDto, id);
        return "redirect:/companies";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        CompanyResponseDto companyResponseDto = companyFacade.findById(id);
        model.addAttribute("company", companyResponseDto);
        model.addAttribute("companyTypes", CompanyType.values());
        return "pages/company/company_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        companyFacade.delete(id);
        return "redirect:/companies";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        CompanyResponseDto companyResponseDto = companyFacade.findById(id);
        model.addAttribute("company", companyResponseDto);
        return "pages/company/company_details";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("name", "name", "name"),
                new HeaderName("company type", "companyType", "company_type"),
                new HeaderName("counterparties", null, null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnNames, PageDataResponseDto<CompanyResponseDto> response) {
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
