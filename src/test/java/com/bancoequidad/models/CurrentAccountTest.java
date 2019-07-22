package com.bancoequidad.models;

import com.bancoequidad.Enum.AccountStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CurrentAccountTest {
    final int EXPECTED_NUMBER_ACCOUNT = 1234987643;
    final int NUMBER_ACCOUNT = 1234987643;
    private Account currentAccount;
    @Before
    public void init(){
        currentAccount = new CurrentAccount(NUMBER_ACCOUNT);
    }

    @Test
    public void shouldHaveAllNecessaryAttributes() {
        final int EXPECTED_ID = 0;
        final double EXPECTED_BALANCE = 0.0;

        assertThat(EXPECTED_ID, is(currentAccount.getId()));
        assertThat(EXPECTED_BALANCE, is(currentAccount.getBalance()));
        assertThat(EXPECTED_NUMBER_ACCOUNT, is(currentAccount.getAccountNumber()));
    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated() {
        AccountStatus expectedStatusAccount = AccountStatus.ACTIVE;

        assertThat(expectedStatusAccount, is(currentAccount.getAccountStatus()));
    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated() {

        final double EXPECTED_INTEREST = 0.00015;

        assertThat(EXPECTED_INTEREST, is(currentAccount.getInterest()));
    }

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException, InvalidValuesException {
        final double AMOUNT = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 0.01;
        final double DISCOUNTED_AMOUNT = AMOUNT * DISCOUNT_DEPOSIT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = AMOUNT - DISCOUNTED_AMOUNT;

        currentAccount.deposit(AMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException, InvalidValuesException {
        final double EXPECTED_AMOUNT = -0.9;

        currentAccount.deposit(EXPECTED_AMOUNT);
    }
    @Test
    public void shouldWithdrawalToBeMade() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException {
        final double AMOUNT_TO_DEPOSIT = 60.0;
        currentAccount.deposit(AMOUNT_TO_DEPOSIT);
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        final double EXPECTED_AMOUNT = currentAccount.getBalance() - AMOUNT_TO_WITHDRAWAL;

        currentAccount.withdraw(AMOUNT_TO_WITHDRAWAL);

        assertThat(currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException, InvalidValuesException, InsufficientValuesException {
        final double WITHDRAWAL_AMOUNT = 4000.0;

        currentAccount.withdraw(WITHDRAWAL_AMOUNT);
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenDepositAmountIsNegative() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = -0.9;

        currentAccount.withdraw(EXPECTED_AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenDepositAmountIsZero() throws NegativeValuesException, InvalidValuesException {
        final double AMOUNT = 0;

        currentAccount.deposit(AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenWithdrawalAmountIsZero() throws NegativeValuesException, InvalidValuesException, OutRangeValuesException, InsufficientValuesException {
        final double AMOUNT = 0;

        currentAccount.withdraw(AMOUNT);
    }

    @Test(expected = InsufficientValuesException.class)
    public void shouldThrowErrorWhenWithdrawalAmountIsGreaterThenActual() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final double AMOUNT = 9;

        currentAccount.withdraw(AMOUNT);
    }

    @Test
    public void shouldDeactivateAccountWhenThisIsDisabled() {
        AccountStatus expectedStatus = AccountStatus.LOCKED;
        currentAccount.disable();

        assertThat(currentAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldActiveAccountWhenThisIsEnabled() {
        AccountStatus expectedStatus = AccountStatus.ACTIVE;
        currentAccount.enable();

        assertThat(currentAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldDetailToBePrinted() {

        int expectedId = 0;
        double expectedBalance = currentAccount.getBalance();
        final AccountStatus expectedAccountStatus = AccountStatus.ACTIVE;
        final String EXPECTED_DETAIL = "Id Account " + expectedId + " Balance Account " + expectedBalance
                + " Account Status " + expectedAccountStatus;

        assertThat(EXPECTED_DETAIL, is(currentAccount.print()));
    }
}
