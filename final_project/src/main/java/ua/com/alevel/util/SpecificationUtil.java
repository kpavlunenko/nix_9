package ua.com.alevel.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.BaseTable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SpecificationUtil {

    private SpecificationUtil() {

    }

    public static List<Predicate> generateSpecificationPredicates(
            Map<String, String[]> parameterMap,
            Class<? extends BaseEntity> entityClass,
            Root<? extends BaseEntity> root,
            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (MapUtils.isEmpty(parameterMap)) {
            return predicates;
        }

        Field[] allFields = FieldUtils.getAllFields(entityClass);
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                String[] params = parameterMap.get(field.getName());
                if (params != null && params.length != 0) {
                    if (BaseEntity.class.isAssignableFrom(field.getType())) {
                        if (params.length == 1 && StringUtils.isNotEmpty(params[0])) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()).get("id"), Long.parseLong(params[0])));
                        }
                    }
                }
            }
        }

        return predicates;
    }

    public static List<Predicate> generateTableSpecificationPredicates(
            Map<String, String[]> parameterMap,
            Class<? extends BaseTable> entityClass,
            Root<? extends BaseTable> root,
            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (MapUtils.isEmpty(parameterMap)) {
            return predicates;
        }

        Field[] allFields = FieldUtils.getAllFields(entityClass);
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                String[] params = parameterMap.get(field.getName());
                if (params != null && params.length != 0) {
                    if (BaseEntity.class.isAssignableFrom(field.getType())) {
                        if (params.length == 1 && StringUtils.isNotEmpty(params[0])) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()).get("id"), Long.parseLong(params[0])));
                        }
                    }
                }
            }
        }

        return predicates;
    }
}
