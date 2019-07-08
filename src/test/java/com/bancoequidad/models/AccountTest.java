package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){

        final int EXPECTEDID = 0;
        final double EXPECTEDINTEREST = 0.00015;
        final double EXPECTEDBALANCE = 0.0;

        Account currentAccount = new Account();

        assertThat(EXPECTEDID, is(currentAccount.getId()));
        assertThat(EXPECTEDINTEREST, is(currentAccount.getINTEREST()));
        assertThat(EXPECTEDBALANCE, is(currentAccount.getBalance()));
    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated(){
        AccountStatus expectedStatusAccount = AccountStatus.ACTIVE;

        Account currentAccount = new Account();

        assertThat(expectedStatusAccount, is(currentAccount.getAccountStatus()));
    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated(){

        final double EXPECTEDINTEREST = 0.00015;

        Account currentAccount = new Account();

        assertThat(EXPECTEDINTEREST, is(currentAccount.getINTEREST()));
    }

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException {
        Account currentAccount = new Account();
        final double EXPECTEDAMOUNT = 45.56;

        currentAccount.deposit(EXPECTEDAMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTEDAMOUNT));
    }

    @Test(expected = NegativeValuesException.class )
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException {
        final double EXPECTEDAMOUNT = -0.9;
        Account currentAccount = new Account();

        currentAccount.deposit(EXPECTEDAMOUNT);
    }

    @Test
    public void shouldDoNotThrowErrorWhenReceivePositiveValues() throws NegativeValuesException {
       final double EXPECTEDAMOUNT = 0.1;
       Account currentAccount = new Account();

        currentAccount.deposit(EXPECTEDAMOUNT);
    }

    @Test
    public void shouldDepositToBeMake() throws NegativeValuesException {
        Account currentAccount = new Account();
        final double EXPECTEDAMOUNT = 0;

        currentAccount.deposit(EXPECTEDAMOUNT );

        assertThat(currentAccount.getBalance() , is(EXPECTEDAMOUNT));
    }

    @Test
    public void shouldWithdrawalToBeMake() throws NegativeValuesException, OutRangeValuesException {
        Account currentAccount = new Account();
        final double EXPECTEDAMOUNT  = 0;

        currentAccount.deposit(EXPECTEDAMOUNT);

        assertThat(currentAccount.getBalance() , is(EXPECTEDAMOUNT));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException {
        Account currentAccount = new Account();
        final double WITHDRAWALAMOUN = 3000.0;

        currentAccount.withdraw(WITHDRAWALAMOUN);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountToTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException {
        Account currentAccount = new Account();
        final double EXPECTEDBALANCEAMOUNT = 45.56;
        final double BALANCEAMOUNT = 45.56;

        currentAccount.withdraw(BALANCEAMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTEDBALANCEAMOUNT));
    }


   @Test
    public void shouldDeactivateAccountWhenThisBeDisabled(){
        Account currentAccount = new Account();
        AccountStatus expectedStatus = AccountStatus.LOCKED;
        currentAccount.disable();

        assertThat(currentAccount.getAccountStatus(),is(expectedStatus));
   }

    @Test
    public void shouldActiveAccountWhenThisBeEnable(){
        Account currentAccount = new Account();
        AccountStatus expectedStatus = AccountStatus.ACTIVE;
        currentAccount.enable();

        assertThat(currentAccount.getAccountStatus(),is(expectedStatus));
    }

    @Test
    public void shouldDetailToBePrinted(){
        Account account = new Account();

        final String expectedDetail = "Id Account " +account.getId() +" Balance Account "+ account.getBalance()
                                      +" Account Status "+ account.getAccountStatus();

        assertThat(expectedDetail,is(account.print()));
    }
}
