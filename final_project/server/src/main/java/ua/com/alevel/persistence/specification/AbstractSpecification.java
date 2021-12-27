package ua.com.alevel.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.Map;

public interface AbstractSpecification<E extends BaseEntity> {
    Specification<E> generateCriteriaPredicate(Map<String, String[]> parameterMap, Class<E> entityClass);
}
