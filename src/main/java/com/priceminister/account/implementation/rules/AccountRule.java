package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;

import java.math.BigDecimal;

/**
 * Checks if the requested operation is permitted.
 */
public interface AccountRule {
    void withdrawPermitted(BigDecimal withdrawAmount, Account account) throws IllegalBalanceException, IllegalWithdrawAmountException;
    void add(BigDecimal sumToAdd) throws IllegalWithdrawAmountException;
}
