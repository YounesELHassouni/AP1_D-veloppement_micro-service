package com.enset.bank_account_service.mappers;

import com.enset.bank_account_service.dto.BankAccountRequestDTO;
import com.enset.bank_account_service.dto.BankAccountResponseDTO;
import com.enset.bank_account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
    public BankAccount fromBankAccountResponseDTO(BankAccountResponseDTO bankAccountResponseDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountResponseDTO,bankAccount);
        return bankAccount;
    }
    public BankAccountRequestDTO ReqfromBankAccount(BankAccount bankAccount) {
        BankAccountRequestDTO bankAccountRequestDTO = new BankAccountRequestDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountRequestDTO);
        return bankAccountRequestDTO;
    }
    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO,bankAccount);
        return bankAccount;
    }
}
