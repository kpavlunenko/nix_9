package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.BankAccountRequestDto;
import ua.com.alevel.api.dto.response.BankAccountResponseDto;

import java.math.BigDecimal;

public interface BankAccountFacade extends BaseFacade<BankAccountRequestDto, BankAccountResponseDto> {
    BigDecimal findBalanceByBankAccount(Long id);
    String getAccountStatement(Long id);
}
