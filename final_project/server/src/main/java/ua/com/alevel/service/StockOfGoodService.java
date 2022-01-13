package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.register.StockOfGood;

import java.util.Date;
import java.util.List;

public interface StockOfGoodService extends BaseTableService<StockOfGood> {
    void deleteByDocumentIdAndName(Long documentId, String documentName);
    List<StockOfGood> getStockOfGoodByNomenclatureIdAndCompanyId(Long nomenclatureId, Long companyId, Date date);
}
