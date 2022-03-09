package br.com.financialapi.account.controller;

import br.com.financialapi.account.exception.AccountNotFoundException;
import br.com.financialapi.account.model.Account;
import br.com.financialapi.account.repository.AccountRepository;
import br.com.financialapi.account.response.AccountResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canCreateaNewAccount(){
        AccountResponse response = restTemplate.postForObject("/account",null,AccountResponse.class);

        assertNotNull(response);
    }

    @Test
    public void canRetrieveByAccountIdWhenExists() {
        given(accountRepository.findByAccountId("ab-1"))
                .willReturn(java.util.Optional.of(new Account(Long.getLong("1"), "ab-1", BigDecimal.valueOf(1.0))));

        ResponseEntity<AccountResponse> accountResponse = restTemplate.getForEntity("/account/ab-1", AccountResponse.class);

        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountResponse.getBody().equals(java.util.Optional.of(new AccountResponse("ab-1", BigDecimal.valueOf(1.0)))));
    }

    @Test
    public void canRetrieveByAccountIdWhenNotExists() {
        given(accountRepository.findByAccountId("ab-1"))
                .willThrow(new AccountNotFoundException());
        ResponseEntity<AccountResponse> accountResponse = restTemplate.getForEntity("/account/ab-1", AccountResponse.class);

        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
