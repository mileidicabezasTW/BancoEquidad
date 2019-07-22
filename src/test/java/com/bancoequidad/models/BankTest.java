package com.bancoequidad.models;

import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {

    Bank bank;

    @Before
    public void init() {
        bank = new Bank();
    }

    @Test
    public void shouldHaveAllNecessaryAttributes() {
        List<Account> expectedAccountsList = new ArrayList<Account>();
        List<Client> expectedClientList = new ArrayList<Client>();
        assertThat(expectedAccountsList, is(bank.accountList));
        assertThat(expectedClientList, is(bank.getClientList()));
    }

    @Test
    public void shouldHaveAClientList() {
        List<Client> expectedList = new ArrayList<>();
        Client client = new Client();
        expectedList.add(client);

        bank.setClientList(expectedList);

        assertThat(bank.getClientList(), is(expectedList));
    }

    @Test
    public void shouldHaveASavingsAccountList() {
        List<Account> expectedAccount = new ArrayList<>();
        Account account = new SavingsAccount(0);
        expectedAccount.add(account);

        bank.setAccountList(expectedAccount);

        assertThat(bank.getAccountList(), is(expectedAccount));
    }

    @Test
    public void shouldHaveACurrentAccountList() {
        List<Account> expectedAccount = new ArrayList<>();
        Account account = new CurrentAccount(0);
        expectedAccount.add(account);

        bank.setAccountList(expectedAccount);

        assertThat(bank.getAccountList(),is(expectedAccount));
    }

    @Test
    public void shouldHaveTransferMade() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException {
        final Account ACCOUNT_NUMBER_ONE = new SavingsAccount(0);
        final Account ACCOUNT_NUMBER_TWO = new SavingsAccount(0);
        final double DEPOSIT_AMOUNT = 4.67;

        bank.doTransfer(ACCOUNT_NUMBER_ONE,ACCOUNT_NUMBER_TWO,DEPOSIT_AMOUNT);
        ACCOUNT_NUMBER_ONE.deposit(DEPOSIT_AMOUNT);
        ACCOUNT_NUMBER_TWO.getBalance();

        assertThat(bank.doTransfer(ACCOUNT_NUMBER_ONE,ACCOUNT_NUMBER_TWO,DEPOSIT_AMOUNT), is(ACCOUNT_NUMBER_TWO.getBalance()));
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
