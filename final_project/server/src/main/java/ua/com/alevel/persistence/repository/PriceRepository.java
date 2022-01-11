package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.directory.Price;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PriceRepository extends AbstractTableRepository<Price> {

    Optional<Price> findByDateAndAndPriceType_IdAndNomenclature_Id(Date date, Long priceTypeId, Long nomenclatureId);
    void deleteAllByPriceType_Id(Long id);
    void deleteAllByNomenclature_Id(Long id);
    @Query(value = "select * from prices where nomenclature_id = :nomenclatureId and priceType_id = :priceTypeId and date <= :date order by date desc limit 1", nativeQuery = true)
    Optional<Price> getNomenclaturePrice(@Param("nomenclatureId") long nomenclature, @Param("priceTypeId") long priceType, @Param("date") Date date);
}
