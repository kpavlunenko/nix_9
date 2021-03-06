package ua.com.alevel.util;

import ua.com.alevel.entity.BaseEntity;

import java.util.List;
import java.util.UUID;

public final class GenerateIdUtil {

    private GenerateIdUtil() {}

    public static String generateId(List<? extends BaseEntity> items) {
        String id = UUID.randomUUID().toString();
        if (items.stream().anyMatch(item -> item.getId().equals(id))) {
            return generateId(items);
        }
        return id;
    }
}
