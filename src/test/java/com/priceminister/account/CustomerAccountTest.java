package com.priceminister.account;


import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalWithdrawAmountException;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.rules.AccountRule;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


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
    private static final int POSITIVE_VALUE = 27;
    public static final double DECIMAL_VAL = 0.33;
    private Account customerAccount;
    private AccountRule rule;

    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }
    
    @Test
    public void empty_account_should_have_zero_balance() {
        //given a a fresh account and a balance of zero
        BigDecimal zeroAmount = BigDecimal.valueOf(0);

        //when we get the account balance
        BigDecimal balance = customerAccount.getBalance();

        //then should be equal to zero
        assertThat(balance).isEqualTo(zeroAmount);
    }

    @Test
    public void add_positive_amount_to_account() throws IllegalWithdrawAmountException {
        //given a fresh account and an amount to add
        BigDecimal amountToAdd = BigDecimal.valueOf(POSITIVE_VALUE);
        BigDecimal oldBalance = customerAccount.getBalance();

        //when we add the amount
        customerAccount.add(amountToAdd);

        //then we should have a new balance of 1
        assertThat(customerAccount.getBalance()).isEqualTo(amountToAdd.add(oldBalance));
    }

    @Test
    public void should_fail_to_withdraw_money_if_no_money_after_operation() throws IllegalBalanceException {
        //given a fresh account with 0 balance and amount to withdraw
        BigDecimal amountToWithdraw = BigDecimal.valueOf(POSITIVE_VALUE);

        //when we withdraw the amount
        //then should throw illegal balance exception
        assertThatExceptionOfType(IllegalBalanceException.class)
                .isThrownBy(() -> customerAccount.withdrawAndReportBalance(amountToWithdraw))
                .withMessage("Illegal account balance: "+customerAccount.getBalance().subtract(amountToWithdraw));
    }

    @Test
    public void should_successfully_withdraw_money_if_positive_amount_after_op() throws IllegalBalanceException, IllegalWithdrawAmountException {
        //given an account with a balance that is bigger than the withdrawal
        BigDecimal amountToWithdraw = BigDecimal.valueOf(POSITIVE_VALUE);
        BigDecimal balance = BigDecimal.valueOf(POSITIVE_VALUE+1);
        customerAccount.add(balance);

        //when we withdraw the amount
        BigDecimal newBalance = customerAccount.withdrawAndReportBalance(amountToWithdraw);

        //then we should have a new balance equal to the difference of the two amounts
        assertThat(newBalance).isEqualTo(balance.subtract(amountToWithdraw));
    }

    @Test
    public void should_add_a_decimal_value_to_balance() throws IllegalWithdrawAmountException {
        //given an account with cash and a decimal amount to add
        customerAccount.add(new BigDecimal(POSITIVE_VALUE));
        BigDecimal addedAmount = new BigDecimal("0.33");

        //when we add a decimal sum
        customerAccount.add(addedAmount);

        //we expect the exact result to be in the balance
        assertThat(customerAccount.getBalance()).isEqualTo(new BigDecimal(POSITIVE_VALUE+".33"));
    }

    @Test
    public void should_withdraw_a_decimal_value_from_balance() throws IllegalBalanceException, IllegalWithdrawAmountException {
        //given an account with cash and a decimal withdraw amount
        customerAccount.add(new BigDecimal(POSITIVE_VALUE));
        BigDecimal withdrawnAmount = new BigDecimal("0.33");

        //when we withdraw the decimal sum
        BigDecimal balance = customerAccount.withdrawAndReportBalance(withdrawnAmount);

        //we expect the exact value for the balance
        assertThat(balance).isEqualTo(new BigDecimal("26.67"));
    }

    @Test
    public void should_not_allow_withdraw_of_negative_amounts() throws IllegalBalanceException, IllegalWithdrawAmountException {
        //given an account with cash and a negative withdraw amount
        customerAccount.add(new BigDecimal(POSITIVE_VALUE));
        BigDecimal withdrawnAmount = new BigDecimal("-3");

        //when we withdraw the decimal sum
        //we expect an IllegalWithdrawAmountException exception
        assertThatExceptionOfType(IllegalWithdrawAmountException.class)
                .isThrownBy(() -> customerAccount.withdrawAndReportBalance(withdrawnAmount))
                .withMessage("Illegal amount: -3");
    }

    @Test
    public void should_not_allow_add_of_negative_amounts() throws IllegalBalanceException, IllegalWithdrawAmountException {
        //given an account with cash and a negative amount to add
        customerAccount.add(new BigDecimal(POSITIVE_VALUE));
        BigDecimal amountToAdd = new BigDecimal("-3");

        //when we withdraw the decimal sum
        //we expect an IllegalWithdrawAmountException exception
        assertThatExceptionOfType(IllegalWithdrawAmountException.class)
                .isThrownBy(() -> customerAccount.add(amountToAdd))
                .withMessage("Illegal amount: -3");
    }

}
