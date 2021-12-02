package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.persistence.dao.AgreementDao;
import ua.com.alevel.persistence.dao.CounterpartyDao;
import ua.com.alevel.persistence.datatable.RequestDataTable;
import ua.com.alevel.persistence.datatable.ResponseDataTable;
import ua.com.alevel.persistence.entity.Counterparty;
import ua.com.alevel.service.CounterpartyService;

import java.util.List;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyDao counterpartyDao;
    private final AgreementDao agreementDao;

    public CounterpartyServiceImpl(CounterpartyDao counterpartyDao, AgreementDao agreementDao) {
        this.counterpartyDao = counterpartyDao;
        this.agreementDao = agreementDao;
    }

    @Override
    public void create(Counterparty entity) {
        checkInputDataOnValid(entity);
        counterpartyDao.create(entity);
    }

    @Override
    public void update(Counterparty entity) {
        if (!counterpartyDao.existById(entity.getId())) {
            throw new EntityNotFoundException("counterparty not found");
        }
        checkInputDataOnValid(entity);
        counterpartyDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!counterpartyDao.existById(id)) {
            throw new EntityNotFoundException("counterparty not found");
        }
        long countAgreements =  agreementDao.countByCounterparty(id);
        if (countAgreements != 0) {
            throw new EntityNotFoundException("With this counterparty exist " + countAgreements + " agreements, you can't delete this counterparty.");
        }
        counterpartyDao.delete(id);
    }

    @Override
    public Counterparty findById(Long id) {
        if (!counterpartyDao.existById(id)) {
            throw new EntityNotFoundException("counterparty not found");
        }
        return counterpartyDao.findById(id);
    }

    @Override
    public ResponseDataTable<Counterparty> findAll(RequestDataTable request) {
        ResponseDataTable<Counterparty> responseDataTable = counterpartyDao.findAll(request);
        long count = counterpartyDao.count();
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
    public ResponseDataTable<Counterparty> findAllByCompany(RequestDataTable request, long companyId) {
        ResponseDataTable<Counterparty> responseDataTable = counterpartyDao.findAllByCompany(request, companyId);
        long count = counterpartyDao.countByCompany(companyId);
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
    public List<Counterparty> findAll() {
        List<Counterparty> counterparties = counterpartyDao.findAll();
        return counterparties;
    }

    private void checkInputDataOnValid(Counterparty entity) {
        if (!innIsValid(entity.getInn())) {
            throw new IncorrectInputData("inn is not valid");
        }
        if (!nameIsValid(entity.getName())) {
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean innIsValid(String inn) {
        return inn.matches("[0-9]+") && inn.length() == 12;
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
