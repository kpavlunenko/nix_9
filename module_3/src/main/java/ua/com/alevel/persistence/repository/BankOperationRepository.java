package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.BankOperation;

@Repository
public interface BankOperationRepository extends AbstractRepository<BankOperation> {
}
