package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Counterparty;

public interface CounterpartyDao extends BaseDao<Counterparty> {
    ResponseDataTable<Counterparty> findAllByCompany(RequestDataTable request, long companyId);
    long countByCompany(long companyId);
}
