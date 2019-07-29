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
    Bank bank;

    @Before
    public void init() {
        bank = new Bank();
    }

    @Test//Mal dice Jorge, //check me out again please
    public void shouldHaveAllNecessaryAttributes() {
        final int EXPECTED_SIZE_VALUE = 0;

        assertThat(bank.getAccountList().size(), is(EXPECTED_SIZE_VALUE));
        assertThat(bank.getAccountList().size(), is(EXPECTED_SIZE_VALUE));

    }
    //Here start test for client

    @Test//check me out
    public void shouldHaveAddClientToListOfClients() {
        Client client = new Client("luz", "12357954", MaritalStatus.SINGLE);

        bank.addClient(client);

        assertTrue(bank.getClientList().contains(client));
    }

    //Here start test for create current account
    @Test
    public void shouldHaveCreatedACurrentAccountWithAccountNumber() throws RepeatedValuesExeptions {
        final String accountNumber = "134556589";

        account1 = new CurrentAccount(accountNumber);

        assertThat(bank.createCurrentAccount(accountNumber), is(account1.getAccountNumber()));
    }

    //Here start test for create savings account
    @Test
    public void shouldHaveCreatedASavingsAccountWithAccountNumber() throws RepeatedValuesExeptions {
        final String accountNumber = "134556589";

        account1 = new SavingsAccount(accountNumber);

        assertThat(bank.createSavingsAccount(accountNumber), is(account1.getAccountNumber()));
    }

    //here start test for transfer
    @Test(expected = NegativeValuesException.class)//new Test for transfer
    public void shouldThrowErrorWhenTransferAmountIsLessThenZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        final double TRANSFER_AMOUNT = -89.0;
        Account account1 = new SavingsAccount("1223345");
        Account account2 = new SavingsAccount("13563345");

        bank.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = InvalidValuesException.class)//new Test for transfer
    public void shouldThrowErrorWhenTransferAmountIsEqualToZero() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        final double TRANSFER_AMOUNT = 0;
        Account account1 = new SavingsAccount("1234567");
        Account account2 = new SavingsAccount("53212345");

        bank.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = InsufficientValuesException.class)//new Test for transfer
    public void shouldThrowErrorWhenAccountOriginHaveASmallerBalanceThanWithdrawalAmount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException, RepeatedValuesExeptions {
        final double AMOUNT = 0.9;
        account1 = new CurrentAccount("1234576542");
        account2 = new SavingsAccount("111222001");
        bank.transfer(account1, account2, AMOUNT);

        account1.withdraw(AMOUNT);
    }

    @Test(expected = OutRangeValuesException.class)//new Test for transfer
    public void shouldThroeExceptionWhenMaximumWithdrawalAmountIsExceeded() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException, RepeatedValuesExeptions {
        Account account1 = new SavingsAccount("1112332110");
        Account account2 = new SavingsAccount("1234432222");
        final double TRANSFER_AMOUNT = 4000.0;
        account1.deposit(TRANSFER_AMOUNT);
        bank.transfer(account1, account2, TRANSFER_AMOUNT);
    }

    @Test(expected = RepeatedValuesExeptions.class) //I am new, check me out
    public void shouldThrowErrorInTransferWhenAccountNUmberIsRepeated() throws RepeatedValuesExeptions, InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {
        final String ACCOUNT_NUMBER = "18776543";
        final double AMOUNT = 99.9;
        Account account1 = new SavingsAccount(ACCOUNT_NUMBER);
        Account account2 = new SavingsAccount(ACCOUNT_NUMBER);
        account1.deposit(AMOUNT);
        bank.transfer(account1,account2, AMOUNT);
}

    @Test//check me out
    public void  shouldHaveTransferMadeInDestinationSavingsAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        final double AMOUNT_DEPOSIT = 46.9;
         account1 = new SavingsAccount("11221111");
         account2 = new SavingsAccount("111222333");
         account1.deposit(AMOUNT_DEPOSIT);

        bank.transfer(account1,account2,AMOUNT_DEPOSIT);

        assertThat(account2.getBalance(),is(AMOUNT_DEPOSIT));
    }

    @Test//check me out //tu haces dos veces el descuento
    public void  shouldHaveTransferCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
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
        System.out.println("account1: " + account1.getBalance());
        System.out.println("Expected balance: " + EXPECTED_BALANCE_AMOUNT);
        System.out.println("account1: " + account2.getBalance());
    }

    @Test//check me out
    public void  shouldHaveTransferCurrentAccountAndCurrentAccount() throws InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, RepeatedValuesExeptions {
        account1 = new SavingsAccount("711111112");
        account2 = new CurrentAccount("742111111");
        final double TOTAL_AMOUNT_PERCENTAGE = 100.0;
        final double DISCOUNT_DEPOSIT_PERCENTAGE = 1.0;
        final double AMOUNT_TO_DEPOSIT = 46.9;
        final double DISCOUNTED_AMOUNT = (AMOUNT_TO_DEPOSIT * DISCOUNT_DEPOSIT_PERCENTAGE) / TOTAL_AMOUNT_PERCENTAGE;
        final double EXPECTED_BALANCE_AMOUNT = (AMOUNT_TO_DEPOSIT- DISCOUNTED_AMOUNT);

        account1.deposit(AMOUNT_TO_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);

        assertThat(account2.getBalance(),is(EXPECTED_BALANCE_AMOUNT));
    }

    @Test//check me out
    public void shouldHaveTransferAmountInDestinationAccountAndMakeTheWithdrawalAmountFromTheOriginAccount() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, OutRangeValuesException, RepeatedValuesExeptions {
        account1 = new SavingsAccount("433332111");
        account2 = new SavingsAccount("222210321");
        final double AMOUNT_TO_DEPOSIT = 60.0;
        account1.deposit(AMOUNT_TO_DEPOSIT);
        bank.transfer(account1,account2,AMOUNT_TO_DEPOSIT);
        assertThat(account2.getBalance(), is(AMOUNT_TO_DEPOSIT));
    }
}
