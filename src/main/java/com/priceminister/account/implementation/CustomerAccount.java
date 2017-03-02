package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.WithdrawalStrategy;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalAmountException;

import java.math.BigDecimal;


public class CustomerAccount implements Account {
    private BigDecimal currentBalance;
    private WithdrawalStrategy currentStrategy;

    public CustomerAccount() {
        currentBalance = BigDecimal.ZERO;
        // the strategy by default
        this.currentStrategy = new DebitWithdrawalStrategy(this);
    }

    public CustomerAccount(WithdrawalStrategy withdrawalStrategy) {
        this.currentStrategy = withdrawalStrategy;
        currentStrategy.setAccount(this);
        currentBalance = BigDecimal.ZERO;
    }

    @Override
    public void add(BigDecimal addedAmount) throws IllegalAmountException {
        currentStrategy.add(addedAmount);
    }

    void addAmount(BigDecimal addedAmount) throws IllegalAmountException {
        currentBalance = currentBalance.add(addedAmount);
    }

    @Override
    public BigDecimal getBalance() {
        return currentBalance;
    }

    @Override
    public BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount) throws IllegalBalanceException, IllegalAmountException {
        currentStrategy.withdraw(withdrawnAmount);
        return getBalance();
    }

    void withdraw(BigDecimal withdrawnAmount) {
        currentBalance = currentBalance.subtract(withdrawnAmount);
    }
}
