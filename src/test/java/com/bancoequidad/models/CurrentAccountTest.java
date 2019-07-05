package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
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

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double expectedBalance = 45.56;

        currentAccount.deposit(expectedBalance);

        assertThat(currentAccount.getBalance(), is(expectedBalance));
    }

    @Test(expected = NegativeValuesException.class )
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException {
        final double expectedAmount = -0.9;
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(expectedAmount);
    }

    @Test
    public void shouldDoNotThrowErrorWhenReceivePositiveValues() throws NegativeValuesException {
       final double expectedAmount = 0.1;
       CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(expectedAmount);
    }

    @Test
    public void shouldDepositToBeMake() throws NegativeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double expectedAmount = 0;

        currentAccount.deposit(expectedAmount);
        currentAccount.deposit(currentAccount.makeDeposit());
        assertThat(expectedAmount, is(currentAccount.makeDeposit()));
    }

    @Test
    public void shouldWithdrawalToBeMake() throws NegativeValuesException, OutRangeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double expectedAmount = 0;

        currentAccount.deposit(expectedAmount);
        currentAccount.deposit(currentAccount.makeDeposit());

        assertThat(expectedAmount, is(currentAccount.makeWithdrawal()));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double withdrawalAmount = 3000.0;

        currentAccount.withdraw(withdrawalAmount);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountToTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double expectedBalanceAmount = 45.56;
        final double balanceAmount = 45.56;

        currentAccount.withdraw(balanceAmount);

        assertThat(currentAccount.getBalance(), is(expectedBalanceAmount));
    }


   @Test
    public void shouldDeactivateAccountWhenThisBeDisabled(){
        CurrentAccount currentAccount = new CurrentAccount();
        AccountStatus expectedStatus = AccountStatus.LOCKED;
        currentAccount.disable();

        assertThat(currentAccount.getAccountStatus(),is(expectedStatus));
   }

    @Test
    public void shouldActiveAccountWhenThisBeEnable(){
        CurrentAccount currentAccount = new CurrentAccount();
        AccountStatus expectedStatus = AccountStatus.ACTIVE;
        currentAccount.enable();

        assertThat(currentAccount.getAccountStatus(),is(expectedStatus));
    }

}
