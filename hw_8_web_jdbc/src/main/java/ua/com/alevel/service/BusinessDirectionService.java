package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.BusinessDirection;

public interface BusinessDirectionService extends BaseService<BusinessDirection> {
    ResponseDataTable<BusinessDirection> findAllByCompany(RequestDataTable request, long companyId);
}
