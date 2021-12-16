package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.BankAccountRequestDto;
import ua.com.alevel.api.dto.response.BankAccountResponseDto;
import ua.com.alevel.facade.BankAccountFacade;
import ua.com.alevel.persistence.entity.BankAccount;
import ua.com.alevel.service.BankAccountService;
import ua.com.alevel.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BankAccountFacadeImpl implements BankAccountFacade {

    private final BankAccountService bankAccountService;
    private final UserService userService;

    public BankAccountFacadeImpl(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @Override
    public void create(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setName(bankAccountRequestDto.getName());
        bankAccount.setIban(bankAccountRequestDto.getIban());
        bankAccount.setUser(userService.findById(bankAccountRequestDto.getUserId()).get());
        bankAccountService.create(bankAccount);
    }

    @Override
    public void update(BankAccountRequestDto bankAccountRequestDto, Long id) {
        BankAccount bankAccount = bankAccountService.findById(id).get();
        bankAccount.setName(bankAccountRequestDto.getName());
        bankAccount.setIban(bankAccountRequestDto.getIban());
        bankAccount.setUser(userService.findById(bankAccountRequestDto.getUserId()).get());
        bankAccountService.update(bankAccount);
    }

    @Override
    public void delete(Long id) {
        bankAccountService.delete(id);
    }

    @Override
    public BankAccountResponseDto findById(Long id) {
        return new BankAccountResponseDto(bankAccountService.findById(id).get());
    }

    @Override
    public List<BankAccountResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<BankAccount> all = bankAccountService.findAll(parameterMap);
        List<BankAccountResponseDto> items = all.stream().map(BankAccountResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return bankAccountService.count(parameterMap);
    }

    @Override
    public BigDecimal findBalanceByBankAccount(Long id) {
        return bankAccountService.findBalanceByBankAccount(id);
    }
}
