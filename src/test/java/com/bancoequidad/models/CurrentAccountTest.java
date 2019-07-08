package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CurrentAccountTest {

    @Test
    public void shouldHaveAllNecessaryAttributes(){

        final int EXPECTEDID = 0;
        final double EXPECTEDINTEREST = 0.00015;
        final double EXPECTEDBALANCE = 0.0;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(EXPECTEDID, is(currentAccount.getId()));
        assertThat(EXPECTEDINTEREST, is(currentAccount.getInterest()));
        assertThat(EXPECTEDBALANCE, is(currentAccount.getBalance()));
    }

    @Test
    public void shouldHaveStateActiveWhenAccountIsCreated(){
        AccountStatus expectedStatusAccount = AccountStatus.ACTIVE;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(expectedStatusAccount, is(currentAccount.getAccountStatus()));
    }

    @Test
    public void shouldHaveDefaultInterestWhenAccountIsCreated(){

        final double EXPECTEDINTEREST = 0.00015;

        CurrentAccount currentAccount = new CurrentAccount();

        assertThat(EXPECTEDINTEREST, is(currentAccount.getInterest()));
    }

    @Test
    public void shouldAddDepositAmountToTheBalance() throws NegativeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double EXPECTEDAMOUNT = 45.56;

        currentAccount.deposit(EXPECTEDAMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTEDAMOUNT));
    }

    @Test(expected = NegativeValuesException.class )
    public void shouldThrowErrorWhenReceiveNegativeValuesDeposit() throws NegativeValuesException {
        final double EXPECTEDAMOUNT = -0.9;
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(EXPECTEDAMOUNT);
    }

    @Test
    public void shouldDoNotThrowErrorWhenReceivePositiveValues() throws NegativeValuesException {
       final double EXPECTEDAMOUNT = 0.1;
       CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.deposit(EXPECTEDAMOUNT);
    }

    @Test
    public void shouldDepositToBeMake() throws NegativeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double EXPECTEDAMOUNT = 0;

        currentAccount.deposit(EXPECTEDAMOUNT );
        currentAccount.deposit(currentAccount.makeDeposit());
        assertThat(EXPECTEDAMOUNT , is(currentAccount.makeDeposit()));
    }

    @Test
    public void shouldWithdrawalToBeMake() throws NegativeValuesException, OutRangeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double EXPECTEDAMOUNT  = 0;

        currentAccount.deposit(EXPECTEDAMOUNT);
        currentAccount.deposit(currentAccount.makeDeposit());

        assertThat(EXPECTEDAMOUNT, is(currentAccount.makeWithdrawal()));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThrowErrorWhenMaximumAmountIsExceeded() throws OutRangeValuesException, NegativeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double WITHDRAWALAMOUN = 3000.0;

        currentAccount.withdraw(WITHDRAWALAMOUN);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountToTheCurrentAmount() throws NegativeValuesException, OutRangeValuesException {
        CurrentAccount currentAccount = new CurrentAccount();
        final double EXPECTEDBALANCEAMOUNT = 45.56;
        final double BALANCEAMOUNT = 45.56;

        currentAccount.withdraw(BALANCEAMOUNT);

        assertThat(currentAccount.getBalance(), is(EXPECTEDBALANCEAMOUNT));
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
