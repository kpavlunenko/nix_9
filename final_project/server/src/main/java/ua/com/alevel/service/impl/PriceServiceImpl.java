package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.IncorrectInputData;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudTableRepositoryHelper;
import ua.com.alevel.persistence.entity.register.Price;
import ua.com.alevel.persistence.repository.PriceRepository;
import ua.com.alevel.service.PriceService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final CrudTableRepositoryHelper<Price, PriceRepository> repositoryHelper;
    private final PriceRepository priceRepository;
    private final LoggerService loggerService;

    public PriceServiceImpl(CrudTableRepositoryHelper<Price, PriceRepository> repositoryHelper, PriceRepository priceRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.priceRepository = priceRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(Price entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(priceRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Price entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(priceRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + Price.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(priceRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Price.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> findById(Long id) {
        return repositoryHelper.findById(priceRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Price> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(priceRepository, parameterMap, Price.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> getNomenclaturePrice(Map<String, String[]> parameterMap) {
        if (parameterMap.get("nomenclature") != null && parameterMap.get("priceType") != null && parameterMap.get("date") != null) {
            Long nomenclatureId = 0L;
            Long priceTypeId = 0L;
            Date date = new Date();
            try {
                nomenclatureId = Long.parseLong(parameterMap.get("nomenclature")[0]);
                priceTypeId = Long.parseLong(parameterMap.get("priceType")[0]);
                date = new Date(Long.parseLong(parameterMap.get("date")[0]));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }

            Optional<Price> price = priceRepository.getNomenclaturePrice(nomenclatureId, priceTypeId, date);
            if (price.isPresent()) {
                return price;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(priceRepository, parameterMap, Price.class);
    }

    private void checkInputDataOnValid(Price entity) {
        if (entity.getPrice() == BigDecimal.ZERO || entity.getPrice().floatValue() == 0) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "price can not be 0");
            throw new IncorrectInputData("price can not be 0");
        }
        Optional<Price> priceOptional = priceRepository.findByDateAndAndPriceType_IdAndNomenclature_Id(entity.getDate(), entity.getPriceType().getId(), entity.getNomenclature().getId());
        if (priceOptional.isPresent() && priceOptional.get().getId() != entity.getId()) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "price with current price type is exist for this nomenclature on this date");
            throw new IncorrectInputData("price with current price type is exist for this nomenclature on this date");
        }
    }

}
