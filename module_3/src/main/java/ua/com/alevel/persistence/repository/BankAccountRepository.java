package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.BankAccount;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends AbstractRepository<BankAccount> {
    Optional<BankAccount> findByIban(String iban);
    void deleteAllByUser_Id(Long id);
    List<BankAccount> findAllByUser_Id(Long id);
}
