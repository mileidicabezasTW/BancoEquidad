package com.bancoequidad.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SavingsAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){

        SavingsAccount savingsAccount = new SavingsAccount();

        assertEquals(0, savingsAccount.id);

        assertEquals(0.0,savingsAccount.interest);

        assertEquals(0.0,savingsAccount.balance);




    }

}