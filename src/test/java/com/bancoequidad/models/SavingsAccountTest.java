package com.bancoequidad.models;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SavingsAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){

        //Arrange - Preparar

        SavingsAccount savingsAccount = new SavingsAccount();

        final int id = 0;
        final double expectedBalance = 0;

        //Act - Actuar

        //Assert - Afirmar

        assertThat(id, is(savingsAccount.getId()));
        assertThat(expectedBalance, is(savingsAccount.balance));

    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated(){
        //Arrange
        AccountStatus status = AccountStatus.ACTIVE;

        //act
        SavingsAccount savingsAccount = new SavingsAccount();

        //Assert
        assertThat(status, is(savingsAccount.getAccountStatus()));

    }

    @Test
    public void shouldHaveDefaultlInsterestWhenAccountIsCreated(){

        double interest = 0.0001;

        SavingsAccount savingsAccount = new SavingsAccount();

        assertThat(interest, is(savingsAccount.getInterest()));

    }

}