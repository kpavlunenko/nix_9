package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudTableRepositoryHelper;
import ua.com.alevel.persistence.entity.register.StockOfGood;
import ua.com.alevel.persistence.repository.StockOfGoodRepository;
import ua.com.alevel.service.StockOfGoodService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StockOfGoodServiceImpl implements StockOfGoodService {

    private final CrudTableRepositoryHelper<StockOfGood, StockOfGoodRepository> repositoryHelper;
    private final StockOfGoodRepository stockOfGoodRepository;
    private final LoggerService loggerService;

    public StockOfGoodServiceImpl(CrudTableRepositoryHelper<StockOfGood, StockOfGoodRepository> repositoryHelper, StockOfGoodRepository stockOfGoodRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.stockOfGoodRepository = stockOfGoodRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(StockOfGood entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(stockOfGoodRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(StockOfGood entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(stockOfGoodRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + StockOfGood.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(stockOfGoodRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + StockOfGood.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockOfGood> findById(Long id) {
        return repositoryHelper.findById(stockOfGoodRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockOfGood> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(stockOfGoodRepository, parameterMap, StockOfGood.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(stockOfGoodRepository, parameterMap, StockOfGood.class);
    }

    private void checkInputDataOnValid(StockOfGood entity) {
        if (entity.getCost() == BigDecimal.ZERO) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "cost can not be 0");
            throw new IncorrectInputData("cost can not be 0");
        }
        if (entity.getQuantity() == BigDecimal.ZERO) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "cost can not be 0");
            throw new IncorrectInputData("quantity can not be 0");
        }
    }

    @Override
    @Transactional
    public void deleteByDocumentIdAndName(Long documentId, String documentName) {
        if (documentName.equals("PurchaseInvoice")) {
            Boolean salesExistOnThisPurchaseInvoice = stockOfGoodRepository.existsByDocumentNameAndConsignmentId("SalesInvoice", documentId);
            if (salesExistOnThisPurchaseInvoice) {
                loggerService.commit(LoggerLevel.ERROR, "object: " + StockOfGood.class.getSimpleName() + "; operation: delete; documentId = " + documentId + "; documentName = " + documentName + "; problem = " + "You can not change or delete this document, because exist sales with goods from this document");
                throw new IncorrectInputData("You can not change or delete this document, because exist sales with goods from this document");
            }
        }
        loggerService.commit(LoggerLevel.WARN, "object: " + StockOfGood.class.getSimpleName() + "; stage: start; operation: deleteByDocumentIdAndName; documentId = " + documentId + "; documentName = " + documentName);
        stockOfGoodRepository.deleteAllByDocumentIdAndDocumentName(documentId, documentName);
        loggerService.commit(LoggerLevel.WARN, "object: " + StockOfGood.class.getSimpleName() + "; stage: finish; operation: deleteByDocumentIdAndName; documentId = " + documentId + "; documentName = " + documentName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockOfGood> getStockOfGoodByNomenclatureIdAndCompanyId(Long nomenclatureId, Long companyId, Date date) {
        return stockOfGoodRepository.getStockOfGoodByNomenclatureIdAndCompanyId(nomenclatureId, companyId, date);
    }
}
