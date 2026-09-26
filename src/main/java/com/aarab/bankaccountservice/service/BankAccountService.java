package com.aarab.bankaccountservice.service;

import com.aarab.bankaccountservice.dtos.BankAccountRequestDto;
import com.aarab.bankaccountservice.dtos.BankAccountResponseDTO;

public interface BankAccountService {
     BankAccountResponseDTO addAccount(BankAccountRequestDto bankAccountRequestDto);


     BankAccountResponseDTO updateAccount(String id, BankAccountRequestDto bankAccountRequestDto);
}
