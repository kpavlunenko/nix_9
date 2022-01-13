package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
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

    public StockOfGoodServiceImpl(CrudTableRepositoryHelper<StockOfGood, StockOfGoodRepository> repositoryHelper, StockOfGoodRepository stockOfGoodRepository) {
        this.repositoryHelper = repositoryHelper;
        this.stockOfGoodRepository = stockOfGoodRepository;
    }

    @Override
    @Transactional
    public void create(StockOfGood entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.create(stockOfGoodRepository, entity);
    }

    @Override
    @Transactional
    public void update(StockOfGood entity) {
        checkInputDataOnValid(entity);
        repositoryHelper.update(stockOfGoodRepository, entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryHelper.delete(stockOfGoodRepository, id);
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
            throw new IncorrectInputData("cost can not be 0");
        }
        if (entity.getQuantity() == BigDecimal.ZERO) {
            throw new IncorrectInputData("quantity can not be 0");
        }
    }

    @Override
    @Transactional
    public void deleteByDocumentIdAndName(Long documentId, String documentName) {
        stockOfGoodRepository.deleteAllByDocumentIdAndDocumentName(documentId, documentName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockOfGood> getStockOfGoodByNomenclatureIdAndCompanyId(Long nomenclatureId, Long companyId, Date date) {
        return stockOfGoodRepository.getStockOfGoodByNomenclatureIdAndCompanyId(nomenclatureId, companyId, date);
    }
}
