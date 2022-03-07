package br.com.financialapi.account.controller;

import br.com.financialapi.account.model.Account;
import br.com.financialapi.account.model.mapper.AccountMapper;
import br.com.financialapi.account.request.AccountRequest;
import br.com.financialapi.account.response.AccountResponse;
import br.com.financialapi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private AccountMapper mapper;

    @PostMapping
    public AccountResponse createAccount(){
        Account account = service.createAccount();
        return mapper.toResponse(account);
    }

    @GetMapping("/accountId")
    public AccountResponse getBalance(@PathVariable(value = "accountId") String accountId){


    }

}
