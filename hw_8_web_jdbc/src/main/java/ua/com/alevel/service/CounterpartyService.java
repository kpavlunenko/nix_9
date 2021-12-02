package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Counterparty;

public interface CounterpartyService extends BaseService<Counterparty> {
    ResponseDataTable<Counterparty> findAllByCompany(RequestDataTable request, long companyId);
}
