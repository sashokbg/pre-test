package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;

import java.math.BigDecimal;


public class NoNegativeBalanceRule implements AccountRule {
    @Override
    public void withdrawPermitted(BigDecimal withdrawAmount, Account account) throws IllegalBalanceException {
        BigDecimal resultingAccountBalance = account.getBalance().subtract(withdrawAmount);

        if(resultingAccountBalance.compareTo(BigDecimal.ZERO)<1){
            throw new IllegalBalanceException("Illegal account balance: "+resultingAccountBalance);
        }
    }

    @Override
    public void add(BigDecimal sumToAdd) throws IllegalWithdrawAmountException {
        //no op
    }
}
