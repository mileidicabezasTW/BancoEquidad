package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CurrentAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes() {

        final int EXPECTED_ID = 0;
        final double EXPECTED_INTEREST = 0.00015;
        final double EXPECTED_BALANCE = 0.0;

        Account currentAccount = new CurrentAccount();

        assertThat(EXPECTED_ID, is(currentAccount.getId()));
        assertThat(EXPECTED_INTEREST, is(currentAccount.getInterest()));
        assertThat(EXPECTED_BALANCE, is(currentAccount.getBalance()));
    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated() {
        AccountStatus expectedStatusAccount = AccountStatus.ACTIVE;

        Account currentAccount = new CurrentAccount();

        assertThat(expectedStatusAccount, is(currentAccount.getAccountStatus()));
    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated() {

        final double EXPECTED_INTEREST = 0.00015;

        Account currentAccount = new CurrentAccount();

        assertThat(EXPECTED_INTEREST, is(currentAccount.getInterest()));
    }

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException {
        Account currentAccount = new CurrentAccount();
        final double EXPECTED_AMOUNT = 45.56;

        currentAccount.deposit(EXPECTED_AMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException {
        final double EXPECTED_AMOUNT = -0.9;
        Account currentAccount = new CurrentAccount();

        currentAccount.deposit(EXPECTED_AMOUNT);
    }

    @Test
    public void shouldDoNotThrowErrorWhenReceivePositiveValues() throws NegativeValuesException {
        final double EXPECTED_AMOUNT = 0.1;
        Account currentAccount = new CurrentAccount();

        currentAccount.deposit(EXPECTED_AMOUNT);
    }

    @Test
    public void shouldDepositToBeMake() throws NegativeValuesException {
        Account currentAccount = new CurrentAccount();
        final double EXPECTED_AMOUNT = 0;

        currentAccount.deposit(EXPECTED_AMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldWithdrawalToBeMake() throws NegativeValuesException, OutRangeValuesException {
        Account currentAccount = new CurrentAccount();
        final double EXPECTED_AMOUNT = 0;

        currentAccount.deposit(EXPECTED_AMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException {
        Account currentAccount = new CurrentAccount();
        final double WITHDRAWAL_AMOUNT = 4000.0;

        currentAccount.withdraw(WITHDRAWAL_AMOUNT);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountToTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException {
        Account currentAccount = new CurrentAccount();
        final double EXPECTED_BALANCE_AMOUNT = 45.56;
        final double BALANCE_AMOUNT = 45.56;

        currentAccount.withdraw(BALANCE_AMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test
    public void shouldDeactivateAccountWhenThisBeDisabled() {
        Account currentAccount = new CurrentAccount();
        AccountStatus expectedStatus = AccountStatus.LOCKED;
        currentAccount.disable();

        assertThat(currentAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldActiveAccountWhenThisBeEnable() {
        Account currentAccount = new CurrentAccount();
        AccountStatus expectedStatus = AccountStatus.ACTIVE;
        currentAccount.enable();

        assertThat(currentAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldDetailToBePrinted() {
        Account currentAccount = new CurrentAccount();

        final String expectedDetail = "Id Account " + currentAccount.getId() + " Balance Account " + currentAccount.getBalance()
                + " Account Status " + currentAccount.getAccountStatus();

        assertThat(expectedDetail, is(currentAccount.print()));
    }
}
