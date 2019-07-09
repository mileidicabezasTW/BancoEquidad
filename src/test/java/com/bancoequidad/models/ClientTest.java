package com.bancoequidad.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void shouldThatWhenClientIsCreatedThisHaveTheNecessaryAttributes(){

        Client client = new Client();
        final String EXPECTED_NAME = client.getName();
        final String EXPECTED_ID_NUMBER = client.getIdNumber();
        final MaritalStatus EXPECTED_MARITAL_STATUS = MaritalStatus.SINGLE;


        assertThat(EXPECTED_NAME, is(client.getName()));
        assertThat(EXPECTED_ID_NUMBER, is(client.getIdNumber()));
        assertThat(EXPECTED_MARITAL_STATUS, is(client.getMaritalStatus(MaritalStatus.SINGLE)));
    }

    @Test
    public void shouldClientHaveAAccountsList(){
        Client client = new Client();
        List <Account> expectedAccountList = new ArrayList();

        assertThat(client.clientAccount, is(expectedAccountList));
    }

    @Test
    public void shouldPrintDetail(){
        Client client = new Client();
        String EXPECTED_DETAIL = "Name "+client.getName()+" Id Number "+client.getIdNumber()+
                                " Marital Status "+client.getMaritalStatus(MaritalStatus.SINGLE);

        assertThat(client.print(),is(EXPECTED_DETAIL));
    }
}
