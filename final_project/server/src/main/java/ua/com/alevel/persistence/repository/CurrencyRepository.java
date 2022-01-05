package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.directory.Currency;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends AbstractRepository<Currency> {
    Optional<Currency> findByName(String name);
}
