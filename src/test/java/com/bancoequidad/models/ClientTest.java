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
        final String expectedName = client.getName();
        final String expectedIdNumber = client.getIdNumber();
        MaritalStatus expectedMaritalStatus = MaritalStatus.SINGLE;


        assertThat(expectedName, is(client.getName()));
        assertThat(expectedIdNumber, is(client.getIdNumber()));
        assertThat(expectedMaritalStatus, is(client.getMaritalStatus(MaritalStatus.SINGLE)));
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
        String EXPECTEDDETAIL = "Name "+client.getName()+" Id Number "+client.getIdNumber()+
                                " Marital Status "+client.getMaritalStatus(MaritalStatus.SINGLE);

        assertThat(client.print(),is(EXPECTEDDETAIL));
    }



}
