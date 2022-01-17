package com.sbrf.reboot;

import com.sbrf.reboot.repository.impl.FileAccountRepository;

public class Main {
    public static void main(String[] args) {
        FileAccountRepository accountRepository=new FileAccountRepository("src/main/resources/Accounts.txt");
        System.out.println(accountRepository.read());
        System.out.println(accountRepository.getAllAccountsByClientId(1L));
        accountRepository.updateNumberForClientId(1L,333L,555L);
        System.out.println("---------------");
        System.out.println(accountRepository.read());
        accountRepository.updateNumberForClientId(1L,555L,333L);
    }
}
