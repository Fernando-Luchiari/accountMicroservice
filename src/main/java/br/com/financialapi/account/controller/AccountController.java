package br.com.financialapi.account.controller;

import br.com.financialapi.account.model.Account;
import br.com.financialapi.account.model.mapper.AccountMapper;
import br.com.financialapi.account.request.AccountRequest;
import br.com.financialapi.account.response.AccountResponse;
import br.com.financialapi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private AccountMapper mapper;

    @PostMapping("/account")
    public AccountResponse createAccount(){
        Account account = service.createAccount();
        return mapper.toResponse(account);
    }

    @GetMapping("/account/{accountId}")
    public AccountResponse getBalance(@PathVariable(value = "accountId") String accountId){
        Account account = service.getAccountByAccountId(accountId);
        return mapper.toResponse(account);
    }

    @PostMapping("/account/credit")
    public AccountResponse creditAmount(@Valid @RequestBody AccountRequest request){
        Account account = service.updateBalance(request.getAccountId(), request.getAmount(),1);
        return mapper.toResponse(account);
    }

    @PostMapping("/account/withdraw")
    public AccountResponse withdrawAmount(@Valid @RequestBody AccountRequest request){
        Account account = service.updateBalance(request.getAccountId(), request.getAmount(),2);
        return mapper.toResponse(account);
    }

}
