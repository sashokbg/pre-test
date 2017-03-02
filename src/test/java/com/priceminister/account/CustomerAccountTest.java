package com.priceminister.account;


import com.priceminister.account.implementation.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, then develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    private static final int POSITIVE_VALUE = 1;
    private Account customerAccount;
    private AccountRule rule;

    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }
    
    @Test
    public void empty_account_should_have_zero_balance() {
        //given a a fresh account and a balance of zero
        BigDecimal zeroBalance = BigDecimal.valueOf(0);

        //when we get the account balance
        BigDecimal balance = customerAccount.getBalance();

        //then should be equal to zero
        assertThat(balance).isEqualTo(zeroBalance);
    }

    @Test
    public void add_positive_amount_to_account() {
        //given a fresh account and an amount to add
        BigDecimal amountToAdd = BigDecimal.valueOf(POSITIVE_VALUE);
        BigDecimal oldBalance = customerAccount.getBalance();

        //when we add the amount
        customerAccount.add(amountToAdd);

        //then we should have a new balance of 1
        assertThat(customerAccount.getBalance()).isEqualTo(amountToAdd.add(oldBalance));
    }

    @Test(expected = IllegalBalanceException.class)
    public void should_fail_to_withdraw_money_if_no_money_after_operation() throws IllegalBalanceException {
        //given a fresh account with 0 balance and amount to withdraw
        BigDecimal amountToWithdraw = BigDecimal.valueOf(POSITIVE_VALUE);

        //when we withdraw the amount
        customerAccount.withdrawAndReportBalance(amountToWithdraw, new CustomerAccountRule());

        //then should throw illegal balance exception
    }


    @Test(expected = IllegalBalanceException.class)
    public void should_successfully_withdraw_money_if_positive_amount_after_op() throws IllegalBalanceException {
        //given an account with a balance that is bigger than the withdrawal
        BigDecimal amountToWithdraw = BigDecimal.valueOf(POSITIVE_VALUE);
        BigDecimal balance = BigDecimal.valueOf(POSITIVE_VALUE+1);
        customerAccount.add(balance);

        //when we withdraw the amount
        BigDecimal withdrawedAmount = customerAccount.withdrawAndReportBalance(amountToWithdraw, new CustomerAccountRule());

        //then we should have a new balance equal to the difference of the two amounts
        assertThat(customerAccount.getBalance()).isEqualTo(balance.subtract(amountToWithdraw));
        //and we should have a new balance equal to the difference of the two amounts
        assertThat(withdrawedAmount).isEqualTo(amountToWithdraw);
    }

    // Also implement missing unit tests for the above functionalities.

}
