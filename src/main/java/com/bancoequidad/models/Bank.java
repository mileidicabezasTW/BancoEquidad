package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    Account savingsAccount = new SavingsAccount(0);
    Account currentAccount = new CurrentAccount(0);
    Client client = new Client("luz","12357954", MaritalStatus.SINGLE);

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

    public double transfer(Account account1, Account account2, double amount) throws NegativeValuesException, OutRangeValuesException, InsufficientValuesException, InvalidValuesException {
        if (amount > account1.getBalance()) {
            throw new InsufficientValuesException();
        }
        account1.withdraw(amount);
        account2.deposit(amount);

       return account2.getBalance();
    }
}
