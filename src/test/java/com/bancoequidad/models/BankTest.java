package com.bancoequidad.models;

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

        bank.client.getIdNumber();
        bank.client.getName();
        bank.client.getMaritalStatus(MaritalStatus.SINGLE);

        expectedClientList.add(client);

        assertThat(expectedClientList, is(bank.getClientList()));
    }


}
