package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.IllegalAmountException;
import com.priceminister.account.exceptions.IllegalBalanceException;

import java.math.BigDecimal;


public class NoNegativeBalanceRule implements OperationRule {
    @Override
    public void withdrawPermitted(BigDecimal withdrawAmount, Account account) throws IllegalBalanceException {
        BigDecimal resultingAccountBalance = account.getBalance().subtract(withdrawAmount);

        if(resultingAccountBalance.compareTo(BigDecimal.ZERO)<1){
            throw new IllegalBalanceException("Illegal account balance: "+resultingAccountBalance);
        }
    }

    @Override
    public void add(BigDecimal sumToAdd) throws IllegalAmountException {
        //no op
    }
}
