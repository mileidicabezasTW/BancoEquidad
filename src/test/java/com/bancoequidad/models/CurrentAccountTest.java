package com.bancoequidad.models;

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
    public void shouldHaveDefaultInterestWhenAcountIsCreated(){

        final double expectedInterest = 0.00015;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(expectedInterest, is(currentAccount.getInterest()));

    }
}
