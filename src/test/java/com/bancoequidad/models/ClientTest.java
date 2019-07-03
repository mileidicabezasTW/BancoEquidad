package com.bancoequidad.models;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void shouldThatWhenClientIsCreatedThisHaveTheNecessaryAttributes(){

        Client client = new Client();
        final String expectedName = null;
        final String expectedIdNumber = null;
        MaritalStatus expectedMaritalStatus = MaritalStatus.SINGLE;

        assertThat(expectedName, is(client.getName()));
        assertThat(expectedIdNumber, is(client.getIdNumber()));
        assertThat(expectedMaritalStatus, is(client.getMaritalStatus()));
    }

}
