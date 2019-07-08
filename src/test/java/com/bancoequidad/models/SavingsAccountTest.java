package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SavingsAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){

        SavingsAccount savingsAccount = new SavingsAccount();

        final int ID = 0;
        final double EXPECTED_BALANCE = 0;

        assertThat(ID, is(savingsAccount.getId()));
        assertThat(EXPECTED_BALANCE, is(savingsAccount.balance));

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

        final double INTEREST = 0.0001;

        SavingsAccount savingsAccount = new SavingsAccount();

        assertThat(INTEREST, is(savingsAccount.getInterest()));

    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException {
        SavingsAccount savingsAccount = new SavingsAccount();

        savingsAccount.withdraw(3000.0);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountToTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException {
        SavingsAccount savingsAccount = new SavingsAccount();
        final double EXPECTED_BALANCE_AMOUNT = 45.56;
        final double BALANCE_AMOUNT = 45.56;

        savingsAccount.withdraw(BALANCE_AMOUNT);

        assertThat(savingsAccount.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

}