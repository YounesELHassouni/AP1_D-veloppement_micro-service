package com.enset.bank_account_service.web;

import com.enset.bank_account_service.dto.BankAccountRequestDTO;
import com.enset.bank_account_service.dto.BankAccountResponseDTO;
import com.enset.bank_account_service.entities.BankAccount;
import com.enset.bank_account_service.entities.Customer;
import com.enset.bank_account_service.repositories.BankAccountRepository;
import com.enset.bank_account_service.repositories.CustomerRepository;
import com.enset.bank_account_service.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQlController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    private List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }


    @QueryMapping
    private BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException("Bank account not found "+id));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return true;
    }
    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }

}


/*
record BankAccountDTO(Double balance, String currency, String type) {

}*/
