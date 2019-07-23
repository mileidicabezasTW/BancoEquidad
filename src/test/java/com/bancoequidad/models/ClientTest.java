package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.RepeatedValuesExeptions;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {


    Client client;
    final String NAME = null;
    final String ID = null;
    final MaritalStatus MARITAL_STATUS = MaritalStatus.MARRIED;
    @Before
    public void init(){
        client = new Client(NAME, ID,MARITAL_STATUS);
    }

    @Test
    public void shouldHaveTheNecessaryAttributesWhenClientIsCreated(){

        final String EXPECTED_NAME = null;
        final String EXPECTED_ID_NUMBER = null;
        final MaritalStatus EXPECTED_MARITAL_STATUS = MaritalStatus.MARRIED;
        final int EXPECTED_SIZE_VALUE = 0;

        assertThat(EXPECTED_NAME, is(client.getName()));
        assertThat(EXPECTED_ID_NUMBER, is(client.getIdNumber()));
        assertThat(EXPECTED_MARITAL_STATUS, is(client.getMaritalStatus()));
        assertThat(client.getAccountsList().size(),is(EXPECTED_SIZE_VALUE));
    }

    @Test
    public void shouldHaveAAccountsLists() throws RepeatedValuesExeptions {
        Account account = new SavingsAccount(124567);
         //trow exception because there are repeated values.
       client.addAccount(account);

      assertTrue(client.getAccountsList().contains(account));
    }

    @Test(expected = RepeatedValuesExeptions.class)
    public void shouldClientDoNotHaveRepeatAccount() throws RepeatedValuesExeptions {
        Account account = new CurrentAccount(11119);

        client.addAccount(account);
        client.addAccount(account);
    }

    @Test
    public void shouldHaveTheNecessaryParameterForCreateAClient(){
        String expectedName = null;
        String expectedId = null;
        MaritalStatus expectedMaritalStatus = MaritalStatus.MARRIED;

        assertThat(client.getName(),is(expectedName));
        assertThat(client.getIdNumber(),is(expectedId));
        assertThat(client.getMaritalStatus(),is(expectedMaritalStatus));



    }

    @Test
    public void shouldPrintDetail(){
       final String EXPECTED_NAME = null;
       final String EXPECTED_ID = null;
       final MaritalStatus EXPECTED_MARITAL_STATUS = MaritalStatus.SINGLE;

          String EXPECTED_DETAIL = "Name "+EXPECTED_NAME+" Id Number "+EXPECTED_ID+
                                " Marital Status "+EXPECTED_MARITAL_STATUS;

        assertThat(client.print(),is(EXPECTED_DETAIL));
    }
}
