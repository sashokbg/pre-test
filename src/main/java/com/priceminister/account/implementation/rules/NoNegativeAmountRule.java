package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.IllegalAmountException;

import java.math.BigDecimal;

public class NoNegativeAmountRule implements OperationRule {
    @Override
    public void withdrawPermitted(BigDecimal withdrawAmount, Account account) throws IllegalAmountException {
        if (withdrawAmount.compareTo(BigDecimal.ZERO) < 1) {
            throw new IllegalAmountException("Illegal amount: " + withdrawAmount);
        }
    }

    @Override
    public void add(BigDecimal sumToAdd) throws IllegalAmountException {
        if (sumToAdd.compareTo(BigDecimal.ZERO) < 1) {
            throw new IllegalAmountException("Illegal amount: " + sumToAdd);
        }
    }
}
