package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.BankOperationRequestDto;
import ua.com.alevel.api.dto.response.BankOperationResponseDto;
import ua.com.alevel.facade.BankOperationFacade;
import ua.com.alevel.persistence.entity.BankOperation;
import ua.com.alevel.service.BankAccountService;
import ua.com.alevel.service.BankOperationService;
import ua.com.alevel.service.CategoryService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BankOperationFacadeImpl implements BankOperationFacade {

    private final BankOperationService bankOperationService;
    private final CategoryService categoryService;
    private final BankAccountService bankAccountService;

    public BankOperationFacadeImpl(BankOperationService bankOperationService, CategoryService categoryService, BankAccountService bankAccountService) {
        this.bankOperationService = bankOperationService;
        this.categoryService = categoryService;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void create(BankOperationRequestDto bankOperationRequestDto) {
        BankOperation bankOperation = new BankOperation();
        bankOperation.setAmount(bankOperationRequestDto.getAmount());
        bankOperation.setBankOperationType(bankOperationRequestDto.getBankOperationType());
        bankOperation.setOperationType(bankOperationRequestDto.getOperationType());
        bankOperation.setCategory(categoryService.findById(bankOperationRequestDto.getCategoryId()).get());
        bankOperation.setBankAccount(bankAccountService.findById(bankOperationRequestDto.getBankAccountId()).get());
        bankOperation.setRecipientBankAccount(bankAccountService.findById(bankOperationRequestDto.getRecipientBankAccountId()).get());
        bankOperationService.create(bankOperation);
    }

    @Override
    public void update(BankOperationRequestDto bankOperationRequestDto, Long id) {
        BankOperation bankOperation = bankOperationService.findById(id).get();
        bankOperation.setUpdated(new Date());
        bankOperation.setAmount(bankOperationRequestDto.getAmount());
        bankOperation.setBankOperationType(bankOperationRequestDto.getBankOperationType());
        bankOperation.setOperationType(bankOperationRequestDto.getOperationType());
        bankOperation.setCategory(categoryService.findById(bankOperationRequestDto.getCategoryId()).get());
        bankOperation.setBankAccount(bankAccountService.findById(bankOperationRequestDto.getBankAccountId()).get());
        bankOperation.setRecipientBankAccount(bankAccountService.findById(bankOperationRequestDto.getRecipientBankAccountId()).get());
        bankOperationService.update(bankOperation);
    }

    @Override
    public void delete(Long id) {
        bankOperationService.delete(id);
    }

    @Override
    public BankOperationResponseDto findById(Long id) {
        return new BankOperationResponseDto(bankOperationService.findById(id).get());
    }

    @Override
    public List<BankOperationResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<BankOperation> all = bankOperationService.findAll(parameterMap);
        List<BankOperationResponseDto> items = all.stream().map(BankOperationResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return bankOperationService.count(parameterMap);
    }
}
