package com.priceminister.account.implementation;

import com.priceminister.account.WithdrawalStrategy;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;
import com.priceminister.account.implementation.rules.AccountRule;
import com.priceminister.account.implementation.rules.NoNegativeBalanceRule;
import com.priceminister.account.implementation.rules.NoNegativeAmountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DebitWithdrawalStrategy implements WithdrawalStrategy{
    private CustomerAccount account;
    private List<AccountRule> rules;

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
    public void addRule(AccountRule rule) {
        this.rules.add(rule);
    }

    @Override
    public void withdraw(BigDecimal amount) throws IllegalBalanceException, IllegalWithdrawAmountException {
        for(AccountRule rule : rules){
            rule.withdrawPermitted(amount, account);
        }
        account.withdraw(amount);
    }

    @Override
    public void add(BigDecimal amount) throws IllegalWithdrawAmountException {
        for(AccountRule rule : rules){
            rule.add(amount);
        }
        account.addAmount(amount);
    }
}
