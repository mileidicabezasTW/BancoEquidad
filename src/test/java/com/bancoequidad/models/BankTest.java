package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {
    Account account1;
    Account account2;
    Client client;
    Bank bank;

    @Before
    public void init() {
        bank = new Bank();
    }

    @Test
    public void shouldHaveAllNecessaryAttributes() {
        final int EXPECTED_SIZE_VALUE = 0;

        assertThat(bank.getAccountsList().size(), is(EXPECTED_SIZE_VALUE));
        assertThat(bank.getClientList().size(),is(EXPECTED_SIZE_VALUE));
    }
    //Here start test for client
   
    @Test//check me out
    public void shouldHaveAddClientToListOfClients() {
        client = new Client("luz", "12357954", MaritalStatus.SINGLE);

        bank.addClient(client);

        assertTrue(bank.getClientList().contains(client));
    }

    @Test
    public void shouldHaveCreatedASavingsAccountWithAccountNumber() {
        final String accountNumber = "1123321100";

        Account resultAccount = bank.createSavingsAccount(accountNumber);

        assertTrue(bank.getAccountsList().contains(resultAccount));
        assertThat(resultAccount.getAccountNumber(), is(accountNumber));
    }

    //Here start test for create current account
    @Test
    public void shouldHaveCreatedACurrentAccountWithAccountNumber() {
        final String accountNumber = "1123321100";

        Account expectedAccount = bank.createCurrentAccount(accountNumber);

        assertTrue(bank.getAccountsList().contains(expectedAccount));
        assertThat(expectedAccount.getAccountNumber(), is(accountNumber));
    }

    //here start test for transfer
    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenTransferAmountIsLessThenZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        final double TRANSFER_AMOUNT = -89.0;
        Account account1 = new SavingsAccount("1223345");
        Account account2 = new SavingsAccount("13563345");

        bank.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenTransferAmountIsEqualToZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        final double TRANSFER_AMOUNT = 0;
        Account account1 = new SavingsAccount("1234567");
        Account account2 = new SavingsAccount("53212345");

        bank.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = InsufficientValuesException.class)
    public void shouldThrowErrorWhenAccountOriginHaveASmallerBalanceThanWithdrawalAmount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException, RepeatedValuesExeptions {
        final double AMOUNT = 0.9;
        account1 = new CurrentAccount("1234576542");
        account2 = new SavingsAccount("111222001");
        bank.transfer(account1, account2, AMOUNT);

        account1.withdraw(AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceeded() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        Account account1 = new SavingsAccount("1112332110");
        Account account2 = new SavingsAccount("1234432222");
        final double TRANSFER_AMOUNT = 4000.0;
        account1.deposit(TRANSFER_AMOUNT);
        bank.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = RepeatedValuesExeptions.class)
    public void shouldThrowErrorInTransferWhenAccountNUmberIsRepeated() throws RepeatedValuesExeptions, InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final String ACCOUNT_NUMBER = "18776543";
        final double AMOUNT = 99.9;
        Account account1 = new SavingsAccount(ACCOUNT_NUMBER);
        Account account2 = new SavingsAccount(ACCOUNT_NUMBER);
        account1.deposit(AMOUNT);
        bank.transfer(account1,account2, AMOUNT);
}

    @Test
    public void  shouldHaveTransferMadeInDestinationSavingsAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        final double AMOUNT_DEPOSIT = 46.9;
         account1 = new SavingsAccount("11221111");
         account2 = new SavingsAccount("111222333");
         account1.deposit(AMOUNT_DEPOSIT);

        bank.transfer(account1,account2,AMOUNT_DEPOSIT);

        assertThat(account2.getBalance(),is(AMOUNT_DEPOSIT));
    }

    @Test//'Check me out' add assert for obtains the balance account 1
    public void  shouldHaveTransferCurrentAccountToOtherCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new CurrentAccount("122233111");
        account2 = new CurrentAccount("544411111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double AMOUNT_DEPOSIT = 48.9;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT- DISCOUNTED_AMOUNT);
        final double DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT = (AMOUNT_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT_ACCOUNT1 = (AMOUNT_DEPOSIT - DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT) - AMOUNT_TO_DEPOSIT;

        account1.deposit(AMOUNT_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
        assertThat(account1.getBalance(),is(EXPECTED_BALANCE_AMOUNT_ACCOUNT1));
    }

    @Test
    public void  shouldHaveTransferSavingsAccountToACurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new SavingsAccount("711111112");
        account2 = new CurrentAccount("742111111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT- DISCOUNTED_AMOUNT);
        final double EXPECTED_BALANCE = 0.0;

        account1.deposit(AMOUNT_TO_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(),is(EXPECTED_BALANCE_AMOUNT));
        assertThat(account1.getBalance(),is(EXPECTED_BALANCE));
    }

    @Test//check me out
    public void  shouldHaveTransferSavingsAccountToOtherSavingsAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new SavingsAccount("711111112");
        account2 = new SavingsAccount("742111111");
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double EXPECTED_BALANCE = 0.0;

        account1.deposit(AMOUNT_TO_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(),is(AMOUNT_TO_DEPOSIT));
        assertThat(account1.getBalance(),is(EXPECTED_BALANCE));
    }

    @Test//'Check me out' New test
    public void  shouldHaveTransferCurrentAccountToOtherSavingsAccounts() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new CurrentAccount("122233111");
        account2 = new SavingsAccount("544411111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double AMOUNT_DEPOSIT = 48.9;
        final double EXPECTED_BALANCE_AMOUNT = AMOUNT_TO_DEPOSIT;
        final double DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT = (AMOUNT_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT_ACCOUNT1 = (AMOUNT_DEPOSIT - DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT) - AMOUNT_TO_DEPOSIT;

        account1.deposit(AMOUNT_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
        assertThat(account1.getBalance(),is(EXPECTED_BALANCE_AMOUNT_ACCOUNT1));
    }

//    @Test
//    public void shouldPrintClientDetail(){
//        final String NAME = "Juan";
//        final String ID = "1234987";
//        final MaritalStatus MARITAL_STATUS = MaritalStatus.MARRIED;
//        final String EXPECTED_DETAIL = "Name "+NAME+" Id Number "+ID+
//                " Marital Status " + MARITAL_STATUS;
//
//       assertThat(bank.printDetail("12345892"),is(EXPECTED_DETAIL));
//    }

    @Test
    public void shouldPrintAccountDetail(){
        Client client = new Client("Jorge", "1234567890", MaritalStatus.MARRIED);
        bank.addClient(client);

        String result = bank.printDetail("1234567890");

        assertThat(result, is(client.print()));
    }



}
