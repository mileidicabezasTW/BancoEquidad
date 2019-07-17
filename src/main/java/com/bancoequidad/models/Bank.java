package com.bancoequidad.models;

import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    Account savingsAccount = new SavingsAccount();
    Account currentAccount = new CurrentAccount();
    Client client = new Client();

    protected List<Account> accountList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public double doTransfer(Account accountNumberOne, Account accountNumberTwo, double depositAmount) throws NegativeValuesException, InvalidValuesException {
        accountNumberOne = new SavingsAccount();
        accountNumberOne .deposit(depositAmount);
        accountNumberOne = accountNumberTwo;

    return accountNumberTwo.getBalance();

    }
}
