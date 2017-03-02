package com.priceminister.account.implementation;

import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;
import com.priceminister.account.implementation.rules.AccountRule;
import com.priceminister.account.WithdrawalStrategy;
import com.priceminister.account.implementation.rules.NoNegativeAmountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreditWithdrawStrategy implements WithdrawalStrategy {
    private CustomerAccount account;
    private List<AccountRule> rules;

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
    public void withdraw(BigDecimal amount) throws IllegalBalanceException, IllegalWithdrawAmountException {
        for (AccountRule rule : rules) {
            rule.withdrawPermitted(amount, account);
        }
        account.withdraw(amount);
    }

    @Override
    public void add(BigDecimal amount) throws IllegalWithdrawAmountException {

    }

    @Override
    public void addRule(AccountRule rule) {
        rules.add(rule);
    }
}
