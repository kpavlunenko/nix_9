package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.register.Price;

import java.util.Map;
import java.util.Optional;

public interface PriceService extends BaseTableService<Price> {
    Optional<Price> getNomenclaturePrice(Map<String, String[]> parameterMap);
}
