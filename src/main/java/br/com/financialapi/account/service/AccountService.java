package br.com.financialapi.account.service;

import br.com.financialapi.account.model.Account;
import br.com.financialapi.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public Account createAccount(){
        Account account = new Account();
        account.setAccountId(UUID.randomUUID().toString());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public Account getAccountByAccountId(){

    }
}
