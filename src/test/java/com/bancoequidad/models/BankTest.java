package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {

    Bank bank;

    @Before
    public void init() {
        bank = new Bank();
    }

    @Test//Mal
    public void shouldHaveAllNecessaryAttributes() {
        final int EXPECTED_SIZE_VALUE = 0;
        assertThat(bank.getAccountList().size(),is(EXPECTED_SIZE_VALUE));
        assertThat(bank.getAccountList().size(),is(EXPECTED_SIZE_VALUE));
    }

    @Test
    public void shouldHaveAClientList() {
        Client client = new Client("luz","12357954", MaritalStatus.SINGLE);

        bank.addClient(client);

        assertTrue(bank.getClientList().contains(client));
    }


    @Test//new Test for transfer
    public void shouldThrowErrorWhenWithdrawalAmountIsEqualOrLessThenZero(){


    }

    @Test//new Test for transfer
    public void shouldThrowErrorWhenDepositAmountIsEqualOrLessThenZero(){


    }

    @Test//new Test for transfer
    public void shouldThrowErrorWhenAccountOriginToHaveABalanceSmallerThenWithdrawalAmount(){

    }

    @Test //new Test for transfer
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceeded(){

    }

    @Test//new Test for transfer
    public void shouldHaveTransferMade() {

    }

    @Test//new Test for transfer
    public void shouldHaveTransferMadeInDestinationAccount() {
        
    }

    @Test
    public void shouldHaveMadeDepositInSavingsAccount() throws NegativeValuesException, InvalidValuesException {
        final double EXPECTED_AMOUNT = 60.0;
        final double AMOUNT = 60.0;
        bank.savingsAccount.deposit(AMOUNT);
        assertThat(bank.savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldHaveWithdrawalMadeInSavingsAccount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = 53.0;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        bank.savingsAccount.deposit(AMOUNT_TO_DEPOSIT);
        bank.savingsAccount.withdraw(AMOUNT_TO_WITHDRAWAL);
        assertThat(bank.savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldHaveMakeDepositInCurrentAccount() throws NegativeValuesException, InvalidValuesException {
        final double EXPECTED_AMOUNT = 33.66;
        final double AMOUNT = 34.0;
        bank.currentAccount.deposit(AMOUNT);
        assertThat(bank.currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldHaveWithdrawalMakeInCurrentAccount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = 52.4;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;

        bank.currentAccount.deposit(AMOUNT_TO_DEPOSIT);
        bank.currentAccount.withdraw(AMOUNT_TO_WITHDRAWAL);

        assertThat(bank.currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }


}
