package br.com.financialapi.account.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountRequest {

    @NotNull(message = "field accountId can't be null")
    @NotEmpty(message = "field accountId can't be empty")
    private String accountId;

    @NotNull(message = "field 'amount' can't be null")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal amount;

    public AccountRequest(){}

    public AccountRequest(@NotNull(message = "field accountId can't be null") @NotEmpty(message = "field accountId can't be empty") String accountId, @NotNull(message = "field 'amount' can't be null") @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
