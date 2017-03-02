/*
 * =============================================================================
 *
 *   PRICE MINISTER APPLICATION
 *   Copyright (c) 2000 Babelstore.
 *   All Rights Reserved.
 *
 *   $Source$
 *   $Revision$
 *   $Date$
 *   $Author$
 *
 * =============================================================================
 */
package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.AccountException;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;

import java.math.BigDecimal;


public class CustomerAccountRule implements AccountRule {
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
