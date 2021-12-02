package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Agreement;

public interface AgreementService extends BaseService<Agreement> {
    ResponseDataTable<Agreement> findAllByCounterparty(RequestDataTable request, long counterpartyId);
}
