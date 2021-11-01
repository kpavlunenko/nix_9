package ua.com.alevel.util;

import ua.com.alevel.entity.BaseEntity;

import java.util.UUID;

public final class GenerateIdUtil {

    private GenerateIdUtil() {}

    public static String generateId(BaseEntity[] items, int countOfCompanies) {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < countOfCompanies; i++) {
            if (items[i].getId().equals(id)) {
                return generateId(items, countOfCompanies);
            }
        }
        return id;
    }
}
