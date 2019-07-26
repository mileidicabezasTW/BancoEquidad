package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {
    Account account1;
    Account account2;
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
    public void shouldHaveAddClientToListOfClients() {
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

    @Test(expected = InsufficientValuesException.class)//new Test for transfer
    public void shouldThrowErrorWhenAccountOriginHaveASmallerBalanceThanWithdrawalAmount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException {
        final double AMOUNT = 0.9;
        Account account1 = new CurrentAccount(123476844);
        Account account2 = new SavingsAccount(234568890);
        bank.transfer(account1,account2,AMOUNT);

        account1.withdraw(AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)//new Test for transfer
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceeded() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException {
        final double TRANSFER_AMOUNT  = 5000.0;


        bank.transfer(account1,account2,TRANSFER_AMOUNT );

    }

    @Test//new Test for transfer for CurrentAccounts
    public void  shouldHaveTransferMadeInDestinationAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final double AMOUNT_DEPOSIT = 46.9;
         account1 = new SavingsAccount(123476844);
         account2 = new SavingsAccount(234567890);
         account1.deposit(AMOUNT_DEPOSIT);

        bank.transfer(account1,account2,AMOUNT_DEPOSIT);

        assertThat(account2.getBalance(),is(AMOUNT_DEPOSIT));
    }

//    @Test//new Test for transfer for CurrentAccounts espera que la transferencia se hecha en dicha cuenta
//    public void  shouldHaveTransferSavingAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
//        final double AMOUNT_DEPOSIT = 46.9;
//        account1 = new SavingsAccount(123476844);
//        account2 = new SavingsAccount(234567890);
//        account1.deposit(AMOUNT_DEPOSIT);
//
//        bank.transfer(account1,account2,AMOUNT_DEPOSIT);
//
//        assertThat(account2.getBalance(),is(AMOUNT_DEPOSIT));
//    }

    //new Test for transfer for CurrentAccounts
    //new Test for transfer for CurrentAccounts and SavingsAccount juntas

// ya esta, hay que revisar   @Test
//    public void shouldHaveMadeDepositInSavingsAccount() throws NegativeValuesException, InvalidValuesException {
//        final double EXPECTED_AMOUNT = 60.0;
//        final double AMOUNT = 60.0;
//
//        bank.savingsAccount.deposit(AMOUNT);
//        assertThat(bank.savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
//    }

    @Test
    public void shouldHaveTransferAmountInDestinationAccountAndMakeTheWithdrawalAmountFromTheOriginAccount(){

    }

    @Test//probar con cuentas 1 y 2
    public void shouldHaveWithdrawalMadeInAccount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = 53.0;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        bank.savingsAccount.deposit(AMOUNT_TO_DEPOSIT);
        bank.savingsAccount.withdraw(AMOUNT_TO_WITHDRAWAL);
        assertThat(bank.savingsAccount.getBalance(), is(EXPECTED_AMOUNT));
    }

    @Test//tu estas mal hay que borrarte y poner las cuentas 1 y 2
    public void shouldHaveWithdrawalMakeInCurrentAccount() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final double EXPECTED_AMOUNT = 52.4;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;

        bank.currentAccount.deposit(AMOUNT_TO_DEPOSIT);
        bank.currentAccount.withdraw(AMOUNT_TO_WITHDRAWAL);

        assertThat(bank.currentAccount.getBalance(), is(EXPECTED_AMOUNT));
    }


}
