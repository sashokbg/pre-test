package com.priceminister.account;


import com.priceminister.account.exceptions.AccountException;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.rules.AccountRule;
import java.math.BigDecimal;

public interface WithdrawalStrategy {
    void setAccount(CustomerAccount account);
    void withdraw(BigDecimal amount) throws IllegalBalanceException, IllegalWithdrawAmountException;
    void add(BigDecimal amount) throws IllegalWithdrawAmountException;
    void addRule(AccountRule rule) throws AccountException;
}
