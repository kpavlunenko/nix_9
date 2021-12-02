package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.BusinessDirection;

public interface BusinessDirectionDao extends BaseDao<BusinessDirection> {
    ResponseDataTable<BusinessDirection> findAllByCompany(RequestDataTable request, long companyId);
    long countByCompany(long companyId);
}
