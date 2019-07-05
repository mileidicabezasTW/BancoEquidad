package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
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

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException {
        SavingsAccount currentAccount = new SavingsAccount();

        currentAccount.withdraw(1500.0);
    }

}