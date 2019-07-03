package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CurrentAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){

        final int expectedId = 0;
        final double expectedInterest = 0.00015;
        final double expectedBalance = 0.0;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(expectedId, is(currentAccount.getId()));
        assertThat(expectedInterest, is(currentAccount.getInterest()));
        assertThat(expectedBalance, is(currentAccount.getBalance()));

    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated(){
        AccountStatus expectedStatusAccount = AccountStatus.ACTIVE;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(expectedStatusAccount, is(currentAccount.getAccountStatus()));
    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated(){

        final double expectedInterest = 0.00015;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(expectedInterest, is(currentAccount.getInterest()));
    }

    @Test(expected = NegativeValuesException.class )
    public void shouldThrowErrorWhenReceiveNegativeValues() throws NegativeValuesException {

        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(-0.9);
    }

    @Test
    public void shouldDoNotThrowErrorWhenReceivePositiveValues() throws NegativeValuesException {
        double expectedAmount = 0.1;
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.deposit(0.1);


    }
}
