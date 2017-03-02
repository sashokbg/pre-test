package com.priceminister.account.implementation;

import com.priceminister.account.exceptions.IllegalAmountException;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.implementation.rules.OperationRule;
import com.priceminister.account.WithdrawalStrategy;
import com.priceminister.account.implementation.rules.NoNegativeAmountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A withdrawal strategy implementation for a credit account - an account that does allow negative balance
 */
public class CreditWithdrawStrategy implements WithdrawalStrategy {
    private CustomerAccount account;
    private List<OperationRule> rules;

    public CreditWithdrawStrategy() {
        rules = new ArrayList<>();

        initRules();
    }

    private void initRules() {
        rules.add(new NoNegativeAmountRule());
    }

    public void setAccount(CustomerAccount account) {
        this.account = account;
    }

    @Override
    public void withdraw(BigDecimal amount) throws IllegalBalanceException, IllegalAmountException {
        validateRules(amount);
        account.withdraw(amount);
    }

    @Override
    public void add(BigDecimal amount) throws IllegalAmountException {

    }

    @Override
    public void addRule(OperationRule rule) {
        rules.add(rule);
    }

    private void validateRules(BigDecimal amount) throws IllegalBalanceException, IllegalAmountException {
        for (OperationRule rule : rules) {
            rule.withdrawPermitted(amount, account);
        }
    }
}
