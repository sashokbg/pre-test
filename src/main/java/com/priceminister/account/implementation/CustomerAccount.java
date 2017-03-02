package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

import java.math.BigDecimal;


public class CustomerAccount implements Account {
    private BigDecimal currentBalance;
    private BigDecimal minimumAllowedValue;

    public CustomerAccount() {
        currentBalance = BigDecimal.ZERO;
        minimumAllowedValue = BigDecimal.ZERO;
    }

    public void add(BigDecimal addedAmount) {
        currentBalance = currentBalance.add(addedAmount);
    }

    public BigDecimal getBalance() {
        return currentBalance;
    }

    public BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount, AccountRule rule)
    		throws IllegalBalanceException {

        BigDecimal newBalance = currentBalance.subtract(withdrawnAmount);
        if(rule.withdrawPermitted(newBalance)){
            return newBalance;
        }
        throw new IllegalBalanceException(newBalance);
    }

}
