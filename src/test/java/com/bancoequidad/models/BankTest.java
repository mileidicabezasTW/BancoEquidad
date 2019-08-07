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

    @Test
    public void shouldHaveAddClientToListOfClients() {
        String id = "1231";
        String name = "marc";
        MaritalStatus maritalStatus = MaritalStatus.SINGLE;

        bank.createClient(id,name,maritalStatus);
        bank.getClientList().add(client);

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

    @Test
    public void  shouldHaveTransferCurrentAccountToOtherCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new CurrentAccount("122233111");
        account2 = new CurrentAccount("544411111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double AMOUNT_DEPOSIT = 48.9;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT- DISCOUNTED_AMOUNT);

        account1.deposit(AMOUNT_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test
    public void  shouldHaveTransferCurrentAccountToCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
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

    @Test
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

    @Test
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

    //Here start test for findClient
    //i am new  check me
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDoNotFindElementInClientsList(){
        String expectedId = "1522";
        String id = "1222";
        String name = "marc";
        MaritalStatus maritalStatus = MaritalStatus.SINGLE;
        client = new Client(id,name,maritalStatus);

        bank.getClientList().add(client);
        bank.findClient(expectedId);
    }

    @Test//refactoring
    public void shouldFindAClientFromTheListOfClients(){
    String id = "1222";
    String name = "marc";
    MaritalStatus maritalStatus = MaritalStatus.SINGLE;
    client = new Client(id,name,maritalStatus);
    bank.getClientList().add(client);

    Client idClient = bank.findClient(id);
    final String ACTUAL_ID_CLIENT = idClient.getIdNumber();

    assertThat(ACTUAL_ID_CLIENT,is(client.getIdNumber()));
    }

    //test for search account list
    //a im new check me
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDoNotFindElementInAccountsList(){
       final String EXPECTED_ACCOUNT_NUMBER = "1110002200";
       final String ACCOUNT_NUMBER = "1113302200";
       bank.createSavingsAccount(EXPECTED_ACCOUNT_NUMBER);

       bank.findAccount(ACCOUNT_NUMBER);
    }

    @Test//refactoring
    public void shouldFindAAccountFromTheListOfAccounts(){
        final String ACCOUNT_NUMBER = "1110002200";
        Account expectedAccount = bank.createSavingsAccount(ACCOUNT_NUMBER);
        final String EXPECTED_ACCOUNT_NUMBER = expectedAccount.getAccountNumber();

        Account actualAccount = bank.findAccount(EXPECTED_ACCOUNT_NUMBER);
        String ACTUAL_ACCOUNT_NUMBER   = actualAccount.getAccountNumber();

        assertThat(ACTUAL_ACCOUNT_NUMBER,is(EXPECTED_ACCOUNT_NUMBER));
    }

    @Test
    public void shouldAssignAAccountToTheClient() throws RepeatedValuesExeptions {
        final String ACCOUNT_NUMBER = "1110002200";
        final String ID = "1123";
        final String NAME = "name";
        MaritalStatus MARITAL_STATUS = MaritalStatus.SINGLE;

        Client client = new Client(ID,NAME,MARITAL_STATUS);

        client.addAccount(bank.createCurrentAccount(ACCOUNT_NUMBER));
        bank.assignAccountToTheClient(client,ACCOUNT_NUMBER);
        bank.findAccount(ACCOUNT_NUMBER);

      assertTrue(client.getAccountsList().contains(ACCOUNT_NUMBER));
    }

    //Here start test for depsit
//    @Test //I am new. i do not think this method is necessary.
//    public void shouldMakeADepositInACurrentAccount() throws NegativeValuesException, InvalidValuesException, RepeatedValuesExeptions {
//        final String accountNumber = "1123321100";
//        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
//        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
//        final double AMOUNT_TO_DEPOSIT = 46.9;
//        final double DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
//        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT- DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT);
//        final String ACCOUNT_NUMBER = bank.createSavingsAccount(accountNumber).getAccountNumber();
//
//        account1 = bank.createCurrentAccount(accountNumber);
//        account1.deposit(AMOUNT_TO_DEPOSIT);
//        bank.createCurrentAccount(accountNumber).deposit(AMOUNT_TO_DEPOSIT);
//
//        assertThat(account1.getBalance(),is(EXPECTED_BALANCE_AMOUNT));
//        assertThat(ACCOUNT_NUMBER,is(accountNumber));
//    }
    //finish


 //informacion de las cuentas asociadas al cliente falta
//    @Test
//    public void shouldPrintAccountDetail() {
//        final String ACCOUNT_NUMBER = "111220030";
//        final String ID_NUMBER = 11123;
//        account1 = new SavingsAccount(ACCOUNT_NUMBER);
//        account1.setId(ID_NUMBER);
//
//        bank.getAccountsList().add(account1);
//        String result = bank.printDetailAccount(ID_NUMBER);
//
//        assertThat(result,is(account1.print()));
//    }

//    @Test
//    public void shouldPrintClientDetail(){
//        Client client = new Client("Jorge", "1234567890", MaritalStatus.MARRIED);
//        bank.createClient(client);
//
//        String result = bank.printDetailClient("1234567890");
//
//        assertThat(result, is(client.print()));
//    }
    //buscar cuentas por numero d cuenta
    //
}
