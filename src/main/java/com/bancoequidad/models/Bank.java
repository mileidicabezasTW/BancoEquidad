package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accountList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void addClient(Client client) {
        this.clientList.add(client);
    }

    public double transfer(Account account1, Account account2, double amount) throws NegativeValuesException, OutRangeValuesException, InsufficientValuesException, InvalidValuesException, RepeatedValuesExeptions {
        if (amount > account1.getBalance()) {
            throw new InsufficientValuesException();
        }
        if(account1.accountNumber == account2.accountNumber){
            throw new RepeatedValuesExeptions();
        }
        account1.withdraw(amount);
        account2.deposit(amount);

       return account2.getBalance();
    }

    public String createCurrentAccount(String accountNumber) {
    return accountNumber;
    }

    public String createSavingsAccount(String accountNumber) {
        return accountNumber;
    }
}
