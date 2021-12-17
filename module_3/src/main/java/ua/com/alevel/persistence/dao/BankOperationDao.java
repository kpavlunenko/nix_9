package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.BankOperation;

import java.util.List;

public interface BankOperationDao extends BaseDao<BankOperation>{
    List<BankOperation> findOperationByBankAccountId(Long id);
}
