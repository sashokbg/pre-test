package com.priceminister.account;

import com.priceminister.account.exceptions.AccountException;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;

import java.math.BigDecimal;

/**
 * This class represents a simple account.
 * It doesn't handle different currencies, all money is supposed to be of standard currency EUR.
 */
public interface Account {
    
    /**
     * Adds money to this account.
     * @param addedAmount - the money to add
     */
    void add(BigDecimal addedAmount) throws IllegalWithdrawAmountException;;
    
    /**
     * Withdraws money from the account.
     * @param withdrawnAmount - the money to withdraw
     * @return the remaining account balance
     * @throws IllegalBalanceException if the withdrawal leaves the account with a forbidden balance
     */
    BigDecimal withdrawAndReportBalance(BigDecimal withdrawnAmount) throws IllegalBalanceException, IllegalWithdrawAmountException;
    
    /**
     * Gets the current account balance.
     * @return the account's balance
     */
    BigDecimal getBalance();
}
