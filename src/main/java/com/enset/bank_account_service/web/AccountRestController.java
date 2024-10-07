package com.enset.bank_account_service.web;

import com.enset.bank_account_service.dto.BankAccountRequestDTO;
import com.enset.bank_account_service.dto.BankAccountResponseDTO;
import com.enset.bank_account_service.entities.BankAccount;
import com.enset.bank_account_service.mappers.AccountMapper;
import com.enset.bank_account_service.repositories.BankAccountRepository;
import com.enset.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping(path = "/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }
    @GetMapping(path = "/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException("Bank account not found "+id));
    }

    @PostMapping(path = "/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }
    @PostMapping(path = "/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException("Bank account not found "+id));
        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency()!= null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getType()!= null) account.setType(bankAccount.getType());
        if (bankAccount.getCreatedAt()!= null) account.setCreatedAt(new Date());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping(path = "/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
