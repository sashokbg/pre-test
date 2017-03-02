package com.priceminister.account;


import com.priceminister.account.exceptions.AccountException;
import com.priceminister.account.exceptions.IllegalAmountException;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.rules.OperationRule;
import java.math.BigDecimal;

/**
 * A strategy interface that combines different rules in order to produce different behaviours
 *
 * @see com.priceminister.account.implementation.DebitWithdrawalStrategy
 * @see com.priceminister.account.implementation.CreditWithdrawStrategy
 */
public interface WithdrawalStrategy {
    void setAccount(CustomerAccount account);
    void withdraw(BigDecimal amount) throws IllegalBalanceException, IllegalAmountException;
    void add(BigDecimal amount) throws IllegalAmountException;
    void addRule(OperationRule rule) throws AccountException;
}
