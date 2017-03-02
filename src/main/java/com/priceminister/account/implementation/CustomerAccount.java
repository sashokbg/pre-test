package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;

import java.math.BigDecimal;


public class CustomerAccount implements Account {
    private BigDecimal currentBalance;

    public CustomerAccount() {
        currentBalance = BigDecimal.ZERO;
    }

    public void add(BigDecimal addedAmount) {
        currentBalance = currentBalance.add(addedAmount);
    }

    public BigDecimal getBalance() {
        return currentBalance;
    }

    public BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException, IllegalWithdrawAmountException {
        if(withdrawnAmount.compareTo(BigDecimal.ZERO) < 1){
            throw new IllegalWithdrawAmountException("Illegal withdraw amount: "+withdrawnAmount);
        }

        BigDecimal newBalance = currentBalance.subtract(withdrawnAmount);
        if(rule.withdrawPermitted(newBalance)){
            withdraw(withdrawnAmount);
            return newBalance;
        }
        throw new IllegalBalanceException("Illegal account balance: "+newBalance);
    }

    private void withdraw(BigDecimal withdrawnAmount) {
        currentBalance = currentBalance.subtract(withdrawnAmount);
    }
}
