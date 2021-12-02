package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.PageAndSizeDataRequestDto;
import ua.com.alevel.view.dto.request.SortDataRequestDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class WebRequestUtil {

    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";
    public static final String DEFAULT_SORT_PARAM_VALUE = "created";
    public static final String DEFAULT_ORDER_PARAM_VALUE = "desc";
    public static final int DEFAULT_PAGE_PARAM_VALUE = 1;
    public static final int DEFAULT_SIZE_PARAM_VALUE = 10;

    private WebRequestUtil() { }

    public static PageAndSizeDataRequestDto generatePageAndSizeData(WebRequest webRequest) {
        int page = webRequest.getParameter(PAGE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(PAGE_PARAM))) : DEFAULT_PAGE_PARAM_VALUE;
        int size = webRequest.getParameter(SIZE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(SIZE_PARAM))) : DEFAULT_SIZE_PARAM_VALUE;
        return new PageAndSizeDataRequestDto(page, size);
    }

    public static PageAndSizeDataRequestDto defaultPageAndSizeData() {
        return new PageAndSizeDataRequestDto(DEFAULT_PAGE_PARAM_VALUE, DEFAULT_SIZE_PARAM_VALUE);
    }

    public static SortDataRequestDto generateSortData(WebRequest webRequest) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(SORT_PARAM)) : DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(ORDER_PARAM)) : DEFAULT_ORDER_PARAM_VALUE;
        return new SortDataRequestDto(sort, order);
    }

    public static Map<String, String> getFiltersFromRequest(WebRequest webRequest) {
        Map<String, String> filters = new HashMap<>();
        if (StringUtils.isNotBlank(webRequest.getParameter("companyId"))) {
            filters.put("companyId", webRequest.getParameter("companyId"));
        }
        return filters;
    }

    public static SortDataRequestDto defaultSortData() {
        return new SortDataRequestDto(DEFAULT_SORT_PARAM_VALUE, DEFAULT_ORDER_PARAM_VALUE);
    }
}
