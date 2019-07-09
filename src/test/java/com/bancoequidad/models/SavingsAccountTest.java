package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SavingsAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes() {
        Account savingsAccount = new SavingsAccount();

        final int ID = 0;
        final double EXPECTED_BALANCE = 0;

        assertThat(ID, is(savingsAccount.getId()));
        assertThat(EXPECTED_BALANCE, is(savingsAccount.balance));
    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated() {
        //Arrange
        AccountStatus status = AccountStatus.ACTIVE;

        //act
        Account savingsAccount = new SavingsAccount();

        //Assert
        assertThat(status, is(savingsAccount.getAccountStatus()));

    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated() {

        final double INTEREST = 0.0001;

        Account savingsAccount = new SavingsAccount();

        assertThat(INTEREST, is(savingsAccount.getInterest()));
    }

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException {
        Account savingsAccount = new SavingsAccount();
        final double EXPECTED_AMOUNT = 45.56;

        savingsAccount.deposit(EXPECTED_AMOUNT);

        assertThat(savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException {
        final double EXPECTED_AMOUNT = -0.9;
        Account savingsAccount = new SavingsAccount();

        savingsAccount.deposit(EXPECTED_AMOUNT);
    }

    @Test
    public void shouldDoNotThrowErrorWhenReceivePositiveValues() throws NegativeValuesException {
        final double EXPECTED_AMOUNT = 0.1;
        Account savingsAccount = new SavingsAccount();

        savingsAccount.deposit(EXPECTED_AMOUNT);
    }

    @Test
    public void shouldDepositToBeMake() throws NegativeValuesException {
        Account savingsAccount = new SavingsAccount();
        final double EXPECTED_AMOUNT = 0;

        savingsAccount.deposit(EXPECTED_AMOUNT);

        assertThat(savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumWithdrawalAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException {
        Account savingsAccount = new SavingsAccount();

        savingsAccount.withdraw(3000.0);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountToTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException {
        Account savingsAccount = new SavingsAccount();
        final double EXPECTED_BALANCE_AMOUNT = 45.56;
        final double BALANCE_AMOUNT = 45.56;

        savingsAccount.withdraw(BALANCE_AMOUNT);

        assertThat(savingsAccount.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test
    public void shouldWithdrawalToBeMake() throws NegativeValuesException, OutRangeValuesException {
        Account currentAccount = new CurrentAccount();
        final double EXPECTED_AMOUNT = 0;

        currentAccount.deposit(EXPECTED_AMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldDeactivateAccountWhenThisBeDisabled() {
        Account savingsAccount = new SavingsAccount();
        AccountStatus expectedStatus = AccountStatus.LOCKED;
        savingsAccount.disable();

        assertThat(savingsAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldActiveAccountWhenThisBeEnable() {
        Account savingsAccount = new SavingsAccount();
        AccountStatus expectedStatus = AccountStatus.ACTIVE;
        savingsAccount.enable();

        assertThat(savingsAccount.getAccountStatus(), is(expectedStatus));
    }

    @Test
    public void shouldDetailToBePrinted() {
        Account savingsAccount = new SavingsAccount();

        final String expectedDetail = "Id Account " + savingsAccount.getId() + " Balance Account " + savingsAccount.getBalance()
                + " Account Status " + savingsAccount.getAccountStatus();

        assertThat(expectedDetail, is(savingsAccount.print()));
    }
}