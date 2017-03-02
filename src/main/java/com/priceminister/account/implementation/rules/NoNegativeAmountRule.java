package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;

import java.math.BigDecimal;

public class NoNegativeAmountRule implements AccountRule {
    @Override
    public void withdrawPermitted(BigDecimal withdrawAmount, Account account) throws IllegalWithdrawAmountException {
        if (withdrawAmount.compareTo(BigDecimal.ZERO) < 1) {
            throw new IllegalWithdrawAmountException("Illegal amount: " + withdrawAmount);
        }
    }

    @Override
    public void add(BigDecimal sumToAdd) throws IllegalWithdrawAmountException {
        if (sumToAdd.compareTo(BigDecimal.ZERO) < 1) {
            throw new IllegalWithdrawAmountException("Illegal amount: " + sumToAdd);
        }
    }
}
