package com.priceminister.account.implementation;

import com.priceminister.account.WithdrawalStrategy;
import com.priceminister.account.exceptions.IllegalAmountException;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.implementation.rules.OperationRule;
import com.priceminister.account.implementation.rules.NoNegativeBalanceRule;
import com.priceminister.account.implementation.rules.NoNegativeAmountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A withdrawal strategy implementation for a debit account - an account that does not allow negative balance
 */
public class DebitWithdrawalStrategy implements WithdrawalStrategy{
    private CustomerAccount account;
    private List<OperationRule> rules;

    public DebitWithdrawalStrategy(CustomerAccount account) {
        this.account = account;
        this.rules = new ArrayList<>();

        initRules();
    }

    private void initRules() {
        rules.add(new NoNegativeBalanceRule());
        rules.add(new NoNegativeAmountRule());
    }

    @Override
    public void addRule(OperationRule rule) {
        this.rules.add(rule);
    }

    @Override
    public void setAccount(CustomerAccount account) {
        this.account = account;
    }

    @Override
    public void withdraw(BigDecimal amount) throws IllegalBalanceException, IllegalAmountException {
        for(OperationRule rule : rules){
            rule.withdrawPermitted(amount, account);
        }
        account.withdraw(amount);
    }

    @Override
    public void add(BigDecimal amount) throws IllegalAmountException {
        for(OperationRule rule : rules){
            rule.add(amount);
        }
        account.addAmount(amount);
    }
}
