package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.BusinessDirectionDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.BusinessDirection;
import ua.com.alevel.service.BusinessDirectionService;

import java.util.List;

@Service
public class BusinessDirectionServiceImpl implements BusinessDirectionService {

    private final BusinessDirectionDao businessDirectionDao;

    public BusinessDirectionServiceImpl(BusinessDirectionDao businessDirectionDao) {
        this.businessDirectionDao = businessDirectionDao;
    }

    @Override
    public void create(BusinessDirection entity) {
        checkInputDataOnValid(entity);
        businessDirectionDao.create(entity);
    }

    @Override
    public void update(BusinessDirection entity) {
        if (!businessDirectionDao.existById(entity.getId())) {
            throw new EntityNotFoundException("business direction not found");
        }
        checkInputDataOnValid(entity);
        businessDirectionDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!businessDirectionDao.existById(id)) {
            throw new EntityNotFoundException("business direction not found");
        }
        businessDirectionDao.delete(id);
    }

    @Override
    public BusinessDirection findById(Long id) {
        if (!businessDirectionDao.existById(id)) {
            throw new EntityNotFoundException("business direction not found");
        }
        return businessDirectionDao.findById(id);
    }

    @Override
    public ResponseDataTable<BusinessDirection> findAll(RequestDataTable request) {
        ResponseDataTable<BusinessDirection> responseDataTable = businessDirectionDao.findAll(request);
        long count = businessDirectionDao.count();
        long entriesFrom = ((request.getCurrentPage() - 1) * request.getPageSize()) + 1;
        long entriesTo = request.getCurrentPage() * request.getPageSize();
        long totalPageSize = 0;
        if (count % request.getPageSize() == 0) {
            totalPageSize = count / request.getPageSize();
        } else {
            totalPageSize = count / request.getPageSize() + 1;
        }
        entriesTo = Math.min(entriesTo, count);
        responseDataTable.setItemsSize(count);
        responseDataTable.setEntriesFrom(entriesFrom);
        responseDataTable.setEntriesTo(entriesTo);
        responseDataTable.setTotalPageSize(totalPageSize);
        return responseDataTable;
    }

    @Override
    public ResponseDataTable<BusinessDirection> findAllByCompany(RequestDataTable request, long companyId) {
        ResponseDataTable<BusinessDirection> responseDataTable = businessDirectionDao.findAllByCompany(request, companyId);
        long count = businessDirectionDao.countByCompany(companyId);
        long entriesFrom = ((request.getCurrentPage() - 1) * request.getPageSize()) + 1;
        long entriesTo = request.getCurrentPage() * request.getPageSize();
        long totalPageSize = 0;
        if (count % request.getPageSize() == 0) {
            totalPageSize = count / request.getPageSize();
        } else {
            totalPageSize = count / request.getPageSize() + 1;
        }
        entriesTo = Math.min(entriesTo, count);
        responseDataTable.setItemsSize(count);
        responseDataTable.setEntriesFrom(entriesFrom);
        responseDataTable.setEntriesTo(entriesTo);
        responseDataTable.setTotalPageSize(totalPageSize);
        return responseDataTable;
    }

    @Override
    public List<BusinessDirection> findAll() {
        List<BusinessDirection> businessDirections = businessDirectionDao.findAll();
        return businessDirections;
    }

    private void checkInputDataOnValid(BusinessDirection entity) {
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
