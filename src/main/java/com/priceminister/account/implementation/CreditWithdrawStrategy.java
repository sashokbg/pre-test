package com.priceminister.account.implementation;

import com.priceminister.account.exceptions.IllegalWithdrawAmountException;
import com.priceminister.account.implementation.rules.AccountRule;
import com.priceminister.account.WithdrawalStrategy;

import java.math.BigDecimal;

public class CreditWithdrawStrategy implements WithdrawalStrategy{
    @Override
    public void withdraw(BigDecimal amount) {
    }

    @Override
    public void add(BigDecimal amount) throws IllegalWithdrawAmountException {

    }

    @Override
    public void addRule(AccountRule rule) {

    }
}
