package ua.com.alevel.persistence.specification.impl;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.BaseTable;
import ua.com.alevel.persistence.specification.AbstractTableSpecification;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public class AbstractTableSpecificationImpl <E extends BaseTable> implements AbstractTableSpecification<E> {

    @Override
    public Specification<E> generateCriteriaPredicate(Map<String, String[]> parameterMap, Class<E> entityClass) {
        return (Specification<E>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = SpecificationUtil.generateTableSpecificationPredicates(parameterMap, entityClass, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
