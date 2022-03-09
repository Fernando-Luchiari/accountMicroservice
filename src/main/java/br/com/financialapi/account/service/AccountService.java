package br.com.financialapi.account.service;

import br.com.financialapi.account.exception.AccountNotFoundException;
import br.com.financialapi.account.model.Account;
import br.com.financialapi.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.SwitchPoint;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(){
        Account account = new Account();
        account.setAccountId(UUID.randomUUID().toString());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public Account getAccountByAccountId(String accountId){
        Optional<Account> opAccount = accountRepository.findByAccountId(accountId);
        if(opAccount.isPresent()){
            return opAccount.get();
        }else{
            throw new AccountNotFoundException();
        }
    }

    public Account updateBalance(String accountId, BigDecimal amount,int type){
        Optional<Account> opAccount = accountRepository.findByAccountId(accountId);
        if(opAccount.isPresent()){
            Account account =  opAccount.get();
            BigDecimal balance = BigDecimal.ZERO;
            switch (type){
                case 1:
                    balance = account.getBalance().add(amount);
                    break;
                case 2:
                    balance = account.getBalance().subtract(amount);
                    break;
            }
            account.setBalance(balance);
            return accountRepository.save(account);
        }else{
            throw new AccountNotFoundException();
        }
    }
}
