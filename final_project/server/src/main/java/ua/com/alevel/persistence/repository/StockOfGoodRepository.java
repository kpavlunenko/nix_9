package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.register.StockOfGood;

import java.util.Date;
import java.util.List;

@Repository
public interface StockOfGoodRepository extends AbstractTableRepository<StockOfGood> {
    void deleteAllByDocumentIdAndDocumentName(Long documentId, String documentName);
    Boolean existsByDocumentNameAndConsignmentId(String documentName, Long documentId);
    @Query(value = "select sum(cost) as cost,\n" +
            "       sum(quantity) as quantity,\n" +
            "       consignment_id,\n" +
            "       id,\n" +
            "       documentId,\n" +
            "       documentName,\n" +
            "       company_id,\n" +
            "       date,\n" +
            "       nomenclature_id\n" +
            "from stock_of_goods\n" +
            "where nomenclature_id = :nomenclatureId\n" +
            "  and company_id = :company_id\n" +
            "  and date <= :date\n" +
            "group by consignment_id\n" +
            "order by date asc", nativeQuery = true)
    List<StockOfGood> getStockOfGoodByNomenclatureIdAndCompanyId(@Param("nomenclatureId") long nomenclatureId, @Param("company_id") long company_id, @Param("date") Date date);
}
