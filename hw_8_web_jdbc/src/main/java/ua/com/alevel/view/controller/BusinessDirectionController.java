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
import ua.com.alevel.facade.BusinessDirectionFacade;
import ua.com.alevel.facade.CompanyFacade;
import ua.com.alevel.view.dto.request.BusinessDirectionRequestDto;
import ua.com.alevel.view.dto.response.BusinessDirectionResponseDto;
import ua.com.alevel.view.dto.response.PageDataResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/business_directions")
public class BusinessDirectionController extends BaseController {

    private final BusinessDirectionFacade businessDirectionFacade;
    private final CompanyFacade companyFacade;

    public BusinessDirectionController(BusinessDirectionFacade businessDirectionFacade, CompanyFacade companyFacade) {
        this.businessDirectionFacade = businessDirectionFacade;
        this.companyFacade = companyFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<BusinessDirectionResponseDto> response = businessDirectionFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/business_directions/all");
        model.addAttribute("createNewItemUrl", "/business_directions/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", true);
        model.addAttribute("cardHeader", "All Business directions");
        return "pages/business_direction/business_direction_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/business_directions", model);
    }

    @GetMapping("/all/company/{companyId}")
    public String findAllByCompany(@PathVariable Long companyId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageDataResponseDto<BusinessDirectionResponseDto> response = businessDirectionFacade.findAllByCompany(request, companyId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/business_directions/all/company/" + companyId);
        model.addAttribute("createNewItemUrl", "/business_directions/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Business directions");
        return "pages/business_direction/business_direction_all";
    }

    @PostMapping("/all/company/{companyId}")
    public ModelAndView findAllByCompanyRedirect(@PathVariable Long companyId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/business_directions/all/company/" + companyId, model);
    }

    @GetMapping("/new")
    public String redirectToNewCompanyPage(Model model) {
        model.addAttribute("businessDirection", new BusinessDirectionRequestDto());
        model.addAttribute("companies", companyFacade.findAll());
        return "pages/business_direction/business_direction_new";
    }

    @PostMapping("/create")
    public String createNewCompany(RedirectAttributes attributes, @ModelAttribute("businessDirection") BusinessDirectionRequestDto businessDirectionRequestDto) {
        businessDirectionFacade.create(businessDirectionRequestDto);
        return "redirect:/business_directions";
    }

    @PostMapping("/update/{id}")
    public String updateBusinessDirection(@PathVariable Long id, @ModelAttribute("businessDirection") BusinessDirectionRequestDto businessDirectionRequestDto) {
        businessDirectionFacade.update(businessDirectionRequestDto, id);
        return "redirect:/business_directions";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        BusinessDirectionResponseDto businessDirectionResponseDto = businessDirectionFacade.findById(id);
        model.addAttribute("businessDirection", businessDirectionResponseDto);
        model.addAttribute("companies", companyFacade.findAll());
        return "pages/business_direction/business_direction_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        businessDirectionFacade.delete(id);
        return "redirect:/business_directions";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        BusinessDirectionResponseDto businessDirectionResponseDto = businessDirectionFacade.findById(id);
        model.addAttribute("businessDirection", businessDirectionResponseDto);
        model.addAttribute("companies", businessDirectionResponseDto.getCompanies());
        return "pages/business_direction/business_direction_details";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("name", "name", "name"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnNames, PageDataResponseDto<BusinessDirectionResponseDto> response) {
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
