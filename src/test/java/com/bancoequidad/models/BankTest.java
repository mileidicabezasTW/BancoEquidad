package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {

    Bank bank;

    @Before
    public void init() {
        bank = new Bank();
    }

    @Test//Mal
    public void shouldHaveAllNecessaryAttributes() {
        final int EXPECTED_SIZE_VALUE = 0;
        assertThat(bank.getAccountList().size(),is(EXPECTED_SIZE_VALUE));
        assertThat(bank.getAccountList().size(),is(EXPECTED_SIZE_VALUE));
    }

    @Test//Cambiar el nombre a algo mas descriptivo, aqui estas probando que los usuarios se agreguen, no que tenga una lista.
    public void shouldHaveAClientList() {
        Client client = new Client("luz","12357954", MaritalStatus.SINGLE);

        bank.addClient(client);

        assertTrue(bank.getClientList().contains(client));
    }


    @Test(expected = NegativeValuesException.class)//new Test for transfer
    public void shouldThrowErrorWhenTransferAmountIsLessThenZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException {
        final double TRANSFER_AMOUNT = -89.0;
        Account account1 = new SavingsAccount(123476844);
        Account account2 = new SavingsAccount(234567890);

        bank.transfer(account1,account2,TRANSFER_AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)//new Test for transfer
    public void shouldThrowErrorWhenTransferAmountIsEqualToZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException {
        final double TRANSFER_AMOUNT = 0;
        Account account1 = new SavingsAccount(123476844);
        Account account2 = new SavingsAccount(234567890);

        bank.transfer(account1,account2,TRANSFER_AMOUNT );
    }
//pasar esta prueba
//    @Test(expected = InsufficientValuesException.class)//new Test for transfer
//    public void shouldThrowErrorWhenAccountOriginToHaveABalanceSmallerThenWithdrawalAmount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException {
//        final double AMOUNT_DEPOSIT = 46.9;
//        final double AMOUNT_WITHDRAWAL = 66.9;
//        Account account1 = new SavingsAccount(123476844);
//        Account account2 = new SavingsAccount(234567890);
//
//        account1.deposit(AMOUNT_DEPOSIT);
//        account2.withdraw(AMOUNT_WITHDRAWAL);
//    }

    @Test(expected = OutRangeValuesException.class)//new Test for transfer
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceeded() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException {
        final double TRANSFER_AMOUNT  = 5000.0;
        Account account1 = new SavingsAccount(123476844);
        Account account2 = new SavingsAccount(234567890);

        bank.transfer(account1,account2,TRANSFER_AMOUNT );

    }

    @Test//new Test for transfer
    public void  shouldHaveTransferMadeInDestinationAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final double AMOUNT_DEPOSIT = 46.9;
        Account account1 = new SavingsAccount(123476844);
        Account account2 = new SavingsAccount(234567890);

        bank.transfer(account1,account2,AMOUNT_DEPOSIT);

        assertThat(account2.getBalance(),is(AMOUNT_DEPOSIT));
    }

    @Test
    public void shouldHaveMadeDepositInSavingsAccount() throws NegativeValuesException, InvalidValuesException {
        final double EXPECTED_AMOUNT = 60.0;
        final double AMOUNT = 60.0;
        bank.savingsAccount.deposit(AMOUNT);
        assertThat(bank.savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldHaveWithdrawalMadeInSavingsAccount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = 53.0;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        bank.savingsAccount.deposit(AMOUNT_TO_DEPOSIT);
        bank.savingsAccount.withdraw(AMOUNT_TO_WITHDRAWAL);
        assertThat(bank.savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test
    public void shouldHaveWithdrawalMakeInCurrentAccount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = 52.4;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;

        bank.currentAccount.deposit(AMOUNT_TO_DEPOSIT);
        bank.currentAccount.withdraw(AMOUNT_TO_WITHDRAWAL);

        assertThat(bank.currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }


}
