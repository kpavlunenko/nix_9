package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.directory.Price;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PriceRepository extends AbstractTableRepository<Price> {
    Optional<Price> findByDateAndAndPriceType_IdAndNomenclature_Id(Date date, Long priceTypeId, Long nomenclatureId);
    void deleteAllByPriceType_Id(Long id);
    void deleteAllByNomenclature_Id(Long id);
}
