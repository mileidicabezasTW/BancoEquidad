package com.bancoequidad.services;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.*;
import com.bancoequidad.models.Account;
import com.bancoequidad.models.Client;
import com.bancoequidad.models.CurrentAccount;
import com.bancoequidad.models.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankDomainServiceTest {
    Account account1;
    Account account2;
    Client client;
    BankDomainService bankDomainService;

    @Before
    public void init() {
        bankDomainService = new BankDomainService();
    }

    @Test
    public void shouldHaveAllNecessaryAttributes() {
        final int EXPECTED_SIZE_VALUE = 0;

        assertThat(bankDomainService.getAccountsList().size(), is(EXPECTED_SIZE_VALUE));
        assertThat(bankDomainService.getClientList().size(), is(EXPECTED_SIZE_VALUE));
    }

    @Test
    public void shouldHaveAddClientToListOfClients() {
        String id = "1231";
        String name = "marc";
        MaritalStatus maritalStatus = MaritalStatus.SINGLE;

        bankDomainService.createClient(id, name, maritalStatus);
        bankDomainService.getClientList().add(client);

        assertTrue(bankDomainService.getClientList().contains(client));
    }

    @Test
    public void shouldHaveCreatedASavingsAccountWithAccountNumber() {
        final String accountNumber = "1123321100";

        Account resultAccount = bankDomainService.createSavingsAccount(accountNumber);

        assertTrue(bankDomainService.getAccountsList().contains(resultAccount));
        assertThat(resultAccount.getAccountNumber(), is(accountNumber));
    }

    @Test
    public void shouldHaveCreatedACurrentAccountWithAccountNumber() {
        final String accountNumber = "1123321100";

        Account expectedAccount = bankDomainService.createCurrentAccount(accountNumber);

        assertTrue(bankDomainService.getAccountsList().contains(expectedAccount));
        assertThat(expectedAccount.getAccountNumber(), is(accountNumber));
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenTransferAmountIsLessThenZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        final double TRANSFER_AMOUNT = -89.0;
        Account account1 = new SavingsAccount("1223345");
        Account account2 = new SavingsAccount("13563345");

        bankDomainService.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenTransferAmountIsEqualToZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        final double TRANSFER_AMOUNT = 0;
        Account account1 = new SavingsAccount("1234567");
        Account account2 = new SavingsAccount("53212345");

        bankDomainService.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = InsufficientValuesException.class)
    public void shouldThrowErrorWhenAccountOriginHaveASmallerBalanceThanWithdrawalAmount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException, RepeatedValuesExeptions {
        final double AMOUNT = 0.9;
        account1 = new CurrentAccount("1234576542");
        account2 = new SavingsAccount("111222001");
        bankDomainService.transfer(account1, account2, AMOUNT);

        account1.withdraw(AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThroeExceptionWhenWithdrawalAmountIsExceededInCurrentAccount() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        String accountNumber1 = "123200000";
        String accountNumber2 = "576387900";
        Account account1 = new CurrentAccount(accountNumber1);
        Account account2 = new CurrentAccount(accountNumber2);
        final double AMOUNT = 5000.0;
        final double TRANSFER_AMOUNT = 4000.0;
        account1.withdraw(AMOUNT);
        account2.deposit(AMOUNT);
        bankDomainService.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThroeExceptionWhenWithdrawalAmountIsExceededInSavingsAccount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, RepeatedValuesExeptions, OutRangeValuesException {
        String accountNumber1 = "123200000";
        String accountNumber2 = "576387900";
        Account account1 = new SavingsAccount(accountNumber1);
        Account account2 = new SavingsAccount(accountNumber2);
        final double TRANSFER_AMOUNT = 3000.0;
        final double DEPOSIT_AMOUNT = 3000.0;

        account1.deposit(DEPOSIT_AMOUNT);
        bankDomainService.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = RepeatedValuesExeptions.class)
    public void shouldThrowErrorInTransferWhenAccountNUmberIsRepeated() throws RepeatedValuesExeptions, InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final String ACCOUNT_NUMBER = "18776543";
        final double AMOUNT = 99.9;
        Account account1 = new SavingsAccount(ACCOUNT_NUMBER);
        Account account2 = new SavingsAccount(ACCOUNT_NUMBER);
        account1.deposit(AMOUNT);
        bankDomainService.transfer(account1, account2, AMOUNT);
    }

    @Test
    public void shouldHaveTransferMadeInDestinationSavingsAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        final double AMOUNT_DEPOSIT = 46.9;
        account1 = new SavingsAccount("11221111");
        account2 = new SavingsAccount("111222333");
        account1.deposit(AMOUNT_DEPOSIT);

        bankDomainService.transfer(account1, account2, AMOUNT_DEPOSIT);

        assertThat(account2.getBalance(), is(AMOUNT_DEPOSIT));
    }

    @Test
    public void shouldHaveTransferCurrentAccountToOtherCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new CurrentAccount("122233111");
        account2 = new CurrentAccount("544411111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double AMOUNT_DEPOSIT = 48.9;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT - DISCOUNTED_AMOUNT);

        account1.deposit(AMOUNT_DEPOSIT);
        bankDomainService.transfer(account1, account2, AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test
    public void shouldHaveTransferCurrentAccountToCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new SavingsAccount("711111112");
        account2 = new CurrentAccount("742111111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT - DISCOUNTED_AMOUNT);
        final double EXPECTED_BALANCE = 0.0;

        account1.deposit(AMOUNT_TO_DEPOSIT);
        bankDomainService.transfer(account1, account2, AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
        assertThat(account1.getBalance(), is(EXPECTED_BALANCE));
    }

    @Test
    public void shouldHaveTransferSavingsAccountToOtherSavingsAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new SavingsAccount("711111112");
        account2 = new SavingsAccount("742111111");
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double EXPECTED_BALANCE = 0.0;

        account1.deposit(AMOUNT_TO_DEPOSIT);
        bankDomainService.transfer(account1, account2, AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(AMOUNT_TO_DEPOSIT));
        assertThat(account1.getBalance(), is(EXPECTED_BALANCE));
    }

    @Test
    public void shouldHaveTransferCurrentAccountToOtherSavingsAccounts() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
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
        bankDomainService.transfer(account1, account2, AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
        assertThat(account1.getBalance(), is(EXPECTED_BALANCE_AMOUNT_ACCOUNT1));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDoNotFindElementInClientsList() {
        String expectedId = "1522";
        String id = "1222";
        String name = "marc";
        MaritalStatus maritalStatus = MaritalStatus.SINGLE;
        bankDomainService.createClient(id, name, maritalStatus);

        bankDomainService.findClient(expectedId);
    }

    @Test
    public void shouldFindAClientFromTheListOfClients() {
        String id = "1222";
        String name = "marc";
        MaritalStatus maritalStatus = MaritalStatus.SINGLE;
        Client expectedResult = bankDomainService.createClient(id, name, maritalStatus);

        Client client = bankDomainService.findClient(id);

        assertThat(client, is(expectedResult));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDoNotFindElementInAccountsList() {
        final String EXPECTED_ACCOUNT_NUMBER = "1110002200";
        final String ACCOUNT_NUMBER = "1113302200";
        bankDomainService.createSavingsAccount(EXPECTED_ACCOUNT_NUMBER);

        bankDomainService.findAccount(ACCOUNT_NUMBER);
    }

    @Test
    public void shouldFindAAccountFromTheListOfAccounts() {
        final String ACCOUNT_NUMBER = "1110002200";
        Account expectedAccount = bankDomainService.createSavingsAccount(ACCOUNT_NUMBER);
        final String EXPECTED_ACCOUNT_NUMBER = expectedAccount.getAccountNumber();

        Account actualAccount = bankDomainService.findAccount(EXPECTED_ACCOUNT_NUMBER);
        String ACTUAL_ACCOUNT_NUMBER = actualAccount.getAccountNumber();

        assertThat(ACTUAL_ACCOUNT_NUMBER, is(EXPECTED_ACCOUNT_NUMBER));
    }

    @Test
    public void shouldAssignAAccountToTheClient() throws RepeatedValuesExeptions {
        final String ACCOUNT_NUMBER = "1110002200";
        final String ID = "1123";
        final String NAME = "name";
        MaritalStatus MARITAL_STATUS = MaritalStatus.SINGLE;
        Account expectedAccount = bankDomainService.createCurrentAccount(ACCOUNT_NUMBER);
        bankDomainService.createClient(ID, NAME, MARITAL_STATUS);

        bankDomainService.assignAccountToTheClient(ID, ACCOUNT_NUMBER);
        Client client = bankDomainService.findClient(ID);

        assertTrue(client.getAccountsList().contains(expectedAccount));
    }

    @Test
    public void shouldMakeADepositInACurrentAccount() throws NegativeValuesException, InvalidValuesException {
        final String ACCOUNT_NUMBER = "1123321100";
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT - DISCOUNTED_AMOUNT_AMOUNT_TO_DEPOSIT);

        Account account = bankDomainService.createCurrentAccount(ACCOUNT_NUMBER);
        bankDomainService.deposit(ACCOUNT_NUMBER, AMOUNT_TO_DEPOSIT);

        assertThat(account.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenDepositAmountEqualToZero() throws NegativeValuesException, InvalidValuesException {
        final String ACCOUNT_NUMBER = "1110002200";
        final double EXPECTED_AMOUNT = 0.0;

        bankDomainService.deposit(ACCOUNT_NUMBER, EXPECTED_AMOUNT);
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesOfDeposit() throws NegativeValuesException, InvalidValuesException {
        final String ACCOUNT_NUMBER = "1110002200";
        final double EXPECTED_AMOUNT = -20.0;

        bankDomainService.deposit(ACCOUNT_NUMBER, EXPECTED_AMOUNT);
    }

    @Test
    public void shouldMakeADepositInASavingAccount() throws NegativeValuesException, InvalidValuesException {
        final String ACCOUNT_NUMBER = "1123321100";
        final double AMOUNT_TO_DEPOSIT = 46.9;

        Account account = bankDomainService.createSavingsAccount(ACCOUNT_NUMBER);
        bankDomainService.deposit(ACCOUNT_NUMBER, AMOUNT_TO_DEPOSIT);

        assertThat(account.getBalance(), is(AMOUNT_TO_DEPOSIT));
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountFromCurrentAccountBalance() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final String ACCOUNT_NUMBER = "1123321100";
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT - DISCOUNTED_AMOUNT) - AMOUNT_TO_WITHDRAWAL;
        Account account = bankDomainService.createCurrentAccount(ACCOUNT_NUMBER);

        bankDomainService.deposit(ACCOUNT_NUMBER, AMOUNT_TO_DEPOSIT);
        bankDomainService.withdrawal(ACCOUNT_NUMBER, AMOUNT_TO_WITHDRAWAL);

        assertThat(account.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceededInSavingsAccount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, RepeatedValuesExeptions, OutRangeValuesException {
        String accountNumber1 = "123200000";
        String accountNumber2 = "576387900";
        final double TRANSFER_AMOUNT = 3000.0;
        final double DEPOSIT_AMOUNT = 3000.0;
        Account account1 = bankDomainService.createSavingsAccount(accountNumber1);
        Account account2 = bankDomainService.createSavingsAccount(accountNumber2);

        bankDomainService.deposit(accountNumber1,DEPOSIT_AMOUNT);
        bankDomainService.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceededInCurrentAccount() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        String accountNumber1 = "123200000";
        String accountNumber2 = "576387900";
        final double TRANSFER_AMOUNT = 4000.0;
        final double DEPOSIT_AMOUNT = 5000.0;
        Account account1 = bankDomainService.createCurrentAccount(accountNumber1);
        Account account2 = bankDomainService.createCurrentAccount(accountNumber2);

        bankDomainService.deposit(accountNumber1,DEPOSIT_AMOUNT);
        bankDomainService.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test
    public void shouldSubtractTheWithdrawalAmountFromSavingsAccountBalance() throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException {
        final String ACCOUNT_NUMBER = "1123321100";
        final double AMOUNT_TO_DEPOSIT = 60.0;
        final double AMOUNT_TO_WITHDRAWAL = 7.0;
        final double EXPECTED_BALANCE_AMOUNT = AMOUNT_TO_DEPOSIT - AMOUNT_TO_WITHDRAWAL;
        Account account = bankDomainService.createSavingsAccount(ACCOUNT_NUMBER);

        bankDomainService.deposit(ACCOUNT_NUMBER, AMOUNT_TO_DEPOSIT);
        bankDomainService.withdrawal(ACCOUNT_NUMBER, AMOUNT_TO_WITHDRAWAL);

        assertThat(account.getBalance(), is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test(expected = InvalidValuesException.class)
    public void shouldThrowErrorWhenWithdrawalMaximumAmountIsEqualToZero() throws OutRangeValuesException, NegativeValuesException, InvalidValuesException, InsufficientValuesException {
        final String ACCOUNT_NUMBER = "1123321100";
        final double WITHDRAWAL_AMOUNT = 0;

        bankDomainService.withdrawal(ACCOUNT_NUMBER, WITHDRAWAL_AMOUNT);
    }

    @Test(expected = NegativeValuesException.class)
    public void shouldThrowErrorWhenReceiveNegativeValuesInTheWithdrawal() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException {
        final String ACCOUNT_NUMBER = "1110002200";
        final double EXPECTED_AMOUNT = -20.0;

        bankDomainService.withdrawal(ACCOUNT_NUMBER, EXPECTED_AMOUNT);
    }

    //informacion de las cuentas asociadas al cliente falta
    @Test
    public void shouldPrintAccountDetail() {
        final String ACCOUNT_NUMBER = "1110002200";
        Account account = bankDomainService.createSavingsAccount(ACCOUNT_NUMBER);

        String result = bankDomainService.printDetailAccount(ACCOUNT_NUMBER);

        assertThat(result, is(account.print()));
    }

    @Test
    public void shouldPrintClientDetail() throws RepeatedValuesExeptions {
        final String ACCOUNT_NUMBER = "1123000001";
        final String ID = "1201";
        final String NAME = "name";
        MaritalStatus MARITAL_STATUS = MaritalStatus.SINGLE;
        Client client = bankDomainService.createClient(ID, NAME, MARITAL_STATUS);
        Account account = bankDomainService.createSavingsAccount(ACCOUNT_NUMBER);
        client.addAccount(account);

        String result = bankDomainService.printDetailClient(ID);

        assertThat(result, is(client.print()));
    }
}
