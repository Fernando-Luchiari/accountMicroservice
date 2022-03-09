package br.com.financialapi.account.model.mapper;

import br.com.financialapi.account.model.Account;
import br.com.financialapi.account.request.AccountRequest;
import br.com.financialapi.account.response.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toResponse(Account account){
        AccountResponse response = new AccountResponse(account.getAccountId(), account.getBalance());
        return response;
    }

}
