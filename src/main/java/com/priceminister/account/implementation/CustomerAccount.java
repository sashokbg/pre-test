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

    @Override
    public void add(BigDecimal addedAmount) throws IllegalWithdrawAmountException {
        checkForNegativeAmount(addedAmount);
        currentBalance = currentBalance.add(addedAmount);
    }

    @Override
    public BigDecimal getBalance() {
        return currentBalance;
    }

    @Override
    public BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException, IllegalWithdrawAmountException {
        checkForNegativeAmount(withdrawnAmount);

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

    private void checkForNegativeAmount(BigDecimal amount) throws IllegalWithdrawAmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 1) {
            throw new IllegalWithdrawAmountException("Illegal amount: " + amount);
        }
    }
}
