package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){
        Bank bank = new Bank();
        List<Account> expectedAccountsList = new ArrayList<Account>();
        List<Client> expectedClientList = new ArrayList<Client>();
        assertThat(expectedAccountsList,is(bank.accountList));
        assertThat(expectedClientList, is(bank.clientList));
    }

    @Test
    public void shouldHaveAClientLIst(){
        List<Client> expectedClientList = new ArrayList<>();
        Bank bank = new Bank();
        Client client = new Client();
        client.getIdNumber();
        client.getName();
        client.getMaritalStatus(MaritalStatus.SINGLE);
        expectedClientList.add(client);

        assertThat(expectedClientList, is(bank.getClientList()));
    }

    @Test
    public void shouldHaveMakeDeposit() throws NegativeValuesException {
        Bank bank = new Bank();
        final double EXPECTED_AMOUNT = 34.0;
        bank.savingsAccount.deposit(EXPECTED_AMOUNT);
        assertThat(EXPECTED_AMOUNT,is(bank.savingsAccount.getBalance()));
    }

    @Test
    public void shouldHaveWithdrawalMake() throws NegativeValuesException, OutRangeValuesException  {
        Bank bank = new Bank();
        final double EXPECTED_AMOUNT = 3.0;
        bank.savingsAccount.withdraw(EXPECTED_AMOUNT);
        assertThat(EXPECTED_AMOUNT, is(bank.savingsAccount.getBalance()));


    }







}
