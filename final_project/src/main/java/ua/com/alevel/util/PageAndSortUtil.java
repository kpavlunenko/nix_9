package ua.com.alevel.util;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Sort;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.Map;

public final class PageAndSortUtil {

    private PageAndSortUtil() {

    }

    public static Integer generatePage(Map<String, String[]> parameterMap) {
        int page = 0;
        if (MapUtils.isNotEmpty(parameterMap)) {
            if (parameterMap.get("currentPage") != null) {
                page = Integer.parseInt(parameterMap.get("currentPage")[0]);
            }
        }
        return page;
    }

    public static Integer generateSize(AbstractRepository repository, Map<String, String[]> parameterMap) {
        int size;
        if (MapUtils.isNotEmpty(parameterMap)) {
            if (parameterMap.get("sizeOfPage") != null) {
                size = Integer.parseInt(parameterMap.get("sizeOfPage")[0]);
            } else {
                size = (int) repository.count();
            }
        } else {
            size = (int) repository.count();
        }
        return size;
    }

    public static Sort generateSort(Map<String, String[]> parameterMap) {
        String sortParam = "id";
        String orderParam = "";
        if (MapUtils.isNotEmpty(parameterMap)) {
            if (parameterMap.get("order") != null && parameterMap.get("sort") != null) {
                if (parameterMap.get("order")[0].equals("desc")) {
                    orderParam = "desc";
                } else {
                    orderParam = "asc";
                }
                sortParam = parameterMap.get("sort")[0];
            }
        } else {
            orderParam = "asc";
        }

        Sort sort = orderParam.equals("desc")
                ? Sort.by(sortParam).descending()
                : Sort.by(sortParam).ascending();
        return sort;
    }
}
