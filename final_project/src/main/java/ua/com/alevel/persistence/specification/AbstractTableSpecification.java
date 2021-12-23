package ua.com.alevel.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.BaseTable;

import java.util.Map;

public interface AbstractTableSpecification <E extends BaseTable> {
    Specification<E> generateCriteriaPredicate(Map<String, String[]> parameterMap, Class<E> entityClass);
}
