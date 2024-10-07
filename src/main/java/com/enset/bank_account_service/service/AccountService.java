package com.enset.bank_account_service.service;

import com.enset.bank_account_service.dto.BankAccountRequestDTO;
import com.enset.bank_account_service.dto.BankAccountResponseDTO;
import com.enset.bank_account_service.entities.BankAccount;
import com.enset.bank_account_service.enums.AccountType;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO);
}
