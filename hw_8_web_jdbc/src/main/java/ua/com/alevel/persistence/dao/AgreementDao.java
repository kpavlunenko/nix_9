package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Agreement;

public interface AgreementDao extends BaseDao<Agreement> {
    ResponseDataTable<Agreement> findAllByCounterparty(RequestDataTable request, long counterpartyId);
    long countByCounterparty(long counterpartyId);
    long countByCompany(long counterpartyId);
}
