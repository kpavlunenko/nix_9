package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.BankAccount;

@Repository
public interface BankAccountRepository extends AbstractRepository<BankAccount> {
}
