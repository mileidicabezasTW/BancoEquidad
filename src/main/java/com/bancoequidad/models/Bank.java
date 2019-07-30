package com.bancoequidad.models;
import com.bancoequidad.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accountsList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void addClient(Client client) {

        this.clientList.add(client);
    }

    public void addAccount(Account account1) {
         this.accountsList.add(account1);
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
