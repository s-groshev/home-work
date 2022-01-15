package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;

import lombok.NonNull;
import java.util.Set;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(@NonNull AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(long clientId,long contractNumber){
        return (accountRepository.getAllAccountsByClientId(clientId)).contains(contractNumber);
    }

    public void addNewAccountForClientId(long clientId,long contractNumber){
        Set<Long> newAccounts = accountRepository.getAllAccountsByClientId(clientId);
        newAccounts.add(contractNumber);
        accountRepository.setAllAccountsByClientId(clientId,newAccounts);
    }
}
