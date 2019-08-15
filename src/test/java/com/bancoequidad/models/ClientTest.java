package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.OnlyStringException;
import com.bancoequidad.exceptions.RepeatedValuesExeptions;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {


    Client client;
    final String ID = "1234987";
    final String NAME = "juan";
    final MaritalStatus MARITAL_STATUS = MaritalStatus.MARRIED;
    @Before
    public void init(){
        client = new Client(ID,NAME,MARITAL_STATUS);
    }

    @Test
    public void shouldHaveTheNecessaryParameterToCreateAClient(){
        final int EXPECTED_SIZE_VALUE = 0;

        new Client(NAME, ID,MARITAL_STATUS);

        assertThat(client.getName(),is(NAME));
        assertThat(client.getIdNumber(),is(ID));
        assertThat(client.getMaritalStatus(),is(MARITAL_STATUS));
        assertThat(client.getAccountsList().size(),is(EXPECTED_SIZE_VALUE));
    }

    @Test
    public void shouldHaveAAccountsLists() throws RepeatedValuesExeptions {
        Account account = new SavingsAccount("18776543");
        client.addAccount(account);

      assertTrue(client.getAccountsList().contains(account));
    }

    @Test(expected = RepeatedValuesExeptions.class)
    public void shouldClientDoNotHaveRepeatAccount() throws RepeatedValuesExeptions {
        String accountNumber = "12000483";
        Account account = new CurrentAccount(accountNumber);

        client.addAccount(account);
        client.addAccount(account);
    }

    @Test
    public void shouldPrintDetail() throws RepeatedValuesExeptions {
        String accountNumber = "12000483";
        Account account = new CurrentAccount(accountNumber);
        client.addAccount(account);

        final  String EXPECTED_DETAIL = "Id Number: "+ID+ "\nName: "+NAME+
                                "\nMarital Status: "+MARITAL_STATUS+"\nAccount number: "+ client.getAccountsList();

        assertThat(client.print(),is(EXPECTED_DETAIL));
    }
}
