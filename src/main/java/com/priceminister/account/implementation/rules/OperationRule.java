package com.priceminister.account.implementation.rules;

import com.priceminister.account.Account;
import com.priceminister.account.exceptions.IllegalAmountException;
import com.priceminister.account.exceptions.IllegalBalanceException;

import java.math.BigDecimal;

/**
 * An interface representing operation rules
 */
public interface OperationRule {
    /**
     * Check if a withdrawal is allowed
     * @param withdrawAmount the amount to be withdrawn from the account
     * @param account the account from which we will be withdrawing money
     * @throws IllegalBalanceException If the end balance is not permitted by the current rule implementation
     * @throws IllegalAmountException If the requested amount is not permitted by the current rule implementation.
     * For example requesting a negative withdrawal
     */
    void withdrawPermitted(BigDecimal withdrawAmount, Account account) throws IllegalBalanceException, IllegalAmountException;

    /**
     * Check if an add operation is allowed
     * @param sumToAdd the amount to add
     * @throws IllegalAmountException if the amount to add is not permitted by the current rule implementation
     */
    void add(BigDecimal sumToAdd) throws IllegalAmountException;
}
