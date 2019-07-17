package com.bancoequidad.models;

import com.bancoequidad.Enum.AccountStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SavingsAccountTest {

    private Account savingsAccount;
    @Before
    public void init(){
        savingsAccount = new SavingsAccount();
    }

    @Test
    public void shouldHaveAllNecessaryAttributes() {
        final int ID = 0;
        final double EXPECTED_BALANCE = 0;

        assertThat(ID, is(savingsAccount.getId()));
        assertThat(EXPECTED_BALANCE, is(savingsAccount.balance));
    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated() {

        AccountStatus status = AccountStatus.ACTIVE;

        assertThat(status, is(savingsAccount.getAccountStatus()));
    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated() {

        final double INTEREST = 0.0001;

        assertThat(INTEREST, is(savingsAccount.getInterest()));
    }

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException, InvalidValuesException {
        final double EXPECTED_AMOUNT = 45.56;
        savingsAccount.deposit(EXPECTED_AMOUNT);

        assertThat(savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException, InvalidValuesException {
        final double EXPECTED_AMOUNT = -0.9;

        savingsAccount.deposit(EXPECTED_AMOUNT);
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesWithdrawal() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = -0.9;

        savingsAccount.withdraw(EXPECTED_AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumWithdrawalAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException, InvalidValuesException, InsufficientValuesException {

        savingsAccount.withdraw(3000.0);
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenDepositAmountIsZero() throws NegativeValuesException, InvalidValuesException {
        final double AMOUNT = 0;

       savingsAccount.deposit(AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenWithdrawalAmountIsZero() throws NegativeValuesException, InvalidValuesException, OutRangeValuesException, InsufficientValuesException {
        final double AMOUNT = 0;

        savingsAccount.withdraw(AMOUNT);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountFromTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        final double EXPECTED_BALANCE_AMOUNT = AMOUNT_TO_DEPOSIT - AMOUNT_TO_WITHDRAWAL;
        savingsAccount.deposit(AMOUNT_TO_DEPOSIT);

        savingsAccount.withdraw(AMOUNT_TO_WITHDRAWAL);

        assertThat(savingsAccount.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test
    public void shouldDeactivateAccountWhenThisBeDisabled() {
        AccountStatus expectedStatus = AccountStatus.LOCKED;
        savingsAccount.disable();

        assertThat(savingsAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldActiveAccountWhenThisBeEnable() {
        AccountStatus expectedStatus = AccountStatus.ACTIVE;
        savingsAccount.enable();

        assertThat(savingsAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldDetailToBePrinted() {
        final String expectedDetail = "Id Account " + savingsAccount.getId() + " Balance Account " + savingsAccount.getBalance()
                + " Account Status " + savingsAccount.getAccountStatus();

        assertThat(expectedDetail, is(savingsAccount.print()));
    }
}