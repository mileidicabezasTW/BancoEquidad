package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.RepeatedValuesExeptions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {

    Client client;
    @Before
    public void init(){
        client = new Client();
    }

    @Test
    public void shouldHaveTheNecessaryAttributesWhenClientIsCreated(){

        final String EXPECTED_NAME = null;
        final String EXPECTED_ID_NUMBER = null;
        final MaritalStatus EXPECTED_MARITAL_STATUS = MaritalStatus.MARRIED;
        final int EXPECTED_SIZE_VALUE = 0;

        assertThat(EXPECTED_NAME, is(client.getName()));
        assertThat(EXPECTED_ID_NUMBER, is(client.getIdNumber()));
        assertThat(EXPECTED_MARITAL_STATUS, is(client.getMaritalStatus(MaritalStatus.MARRIED)));
        assertThat(client.getAccountsList().size(),is(EXPECTED_SIZE_VALUE));
    }

    @Test
    public void shouldHaveAAccountsLists() throws RepeatedValuesExeptions {
        Account account = new CurrentAccount();
        List<Account> accountList = new ArrayList<>();
        account.getId();
        account.getInterest();
        account.getBalance();
        accountList.add(account);

        List<Account> expectedAccount = new ArrayList<>();
        account.getId();
        account.getInterest();
        account.getBalance();
        expectedAccount.add(account);

        client.setAccountsList(expectedAccount);
        client.setAccountsList(accountList);

        assertThat(client.getAccountsList(),is(expectedAccount));
    }

    @Test(expected = RepeatedValuesExeptions.class)
    public void shouldClientDoNotHaveRepeatAccount() throws RepeatedValuesExeptions {
        Account account = new CurrentAccount();
        List<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(account);
        expectedAccounts.contains(account);

        client.setAccountsList(expectedAccounts);

        assertThat(client.getAccountsList(), is(expectedAccounts));
    }

    public void shouldPrintDetail(){
        String expectedName = "Jiras";
        int expectedId = 1;
        MaritalStatus expectedMaritalStatus = MaritalStatus.SINGLE;

          String EXPECTED_DETAIL = "Name "+expectedName+" Id Number "+expectedId+
                                " Marital Status "+expectedMaritalStatus;

        assertThat(client.print(),is(EXPECTED_DETAIL));
    }

    @Test
    public void mytest(){
        Account a = new SavingsAccount();
        Account b = new SavingsAccount();

        assertThat(a, is(b));
    }
}
