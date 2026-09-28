package com.aarab.bankaccountservice.web;

import com.aarab.bankaccountservice.dtos.BankAccountRequestDto;
import com.aarab.bankaccountservice.dtos.BankAccountResponseDTO;
import com.aarab.bankaccountservice.entities.BankAccount;
import com.aarab.bankaccountservice.repositories.BankAccountRepository;
import com.aarab.bankaccountservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.function.DoubleUnaryOperator;

@Controller
public class BankAccountGraphQlController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BankAccountService bankAccountService;
    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDto bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDto bankAccount){
        return bankAccountService.updateAccount(id, bankAccount);
    }
    @MutationMapping(name = "deleteAccount")
    public BankAccount delete(@Argument String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        bankAccountRepository.delete(bankAccount);
        return bankAccount;
    }


}

//record BankAccountDTO(Double balance, String currency, String accountType) {
//
//}