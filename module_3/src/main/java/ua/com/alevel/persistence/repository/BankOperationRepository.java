package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.BankOperation;

import java.math.BigDecimal;

@Repository
public interface BankOperationRepository extends AbstractRepository<BankOperation> {
    void deleteAllByBankAccount_Id(Long id);

    @Query("select sum(bankOperation.amount) from BankOperation bankOperation where bankOperation.bankAccount.id = :id")
    BigDecimal findBalanceByBankAccount(@Param("id") long id);
}
