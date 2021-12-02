package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Agreement;
import ua.com.alevel.service.AgreementService;

import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementDao agreementDao;

    public AgreementServiceImpl(AgreementDao agreementDao) {
        this.agreementDao = agreementDao;
    }

    @Override
    public void create(Agreement entity) {
        checkInputDataOnValid(entity);
        agreementDao.create(entity);
    }

    @Override
    public void update(Agreement entity) {
        if (!agreementDao.existById(entity.getId())) {
            throw new EntityNotFoundException("agreement not found");
        }
        checkInputDataOnValid(entity);
        agreementDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!agreementDao.existById(id)) {
            throw new EntityNotFoundException("agreement not found");
        }
        agreementDao.delete(id);
    }

    @Override
    public Agreement findById(Long id) {
        if (!agreementDao.existById(id)) {
            throw new EntityNotFoundException("agreement not found");
        }
        return agreementDao.findById(id);
    }

    @Override
    public ResponseDataTable<Agreement> findAll(RequestDataTable request) {
        ResponseDataTable<Agreement> responseDataTable = agreementDao.findAll(request);
        long count = agreementDao.count();
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
    public ResponseDataTable<Agreement> findAllByCounterparty(RequestDataTable request, long counterpartyId) {
        ResponseDataTable<Agreement> responseDataTable = agreementDao.findAllByCounterparty(request, counterpartyId);
        long count = agreementDao.countByCounterparty(counterpartyId);
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
    public List<Agreement> findAll() {
        List<Agreement> agreements = agreementDao.findAll();
        return agreements;
    }

    private void checkInputDataOnValid(Agreement entity) {
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
