package com.bancoequidad.models;
import com.bancoequidad.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {

    private List<Account> accountsList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    //renplazar por crear cliente
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

    public Account createCurrentAccount(String accountNumber) {
        Account account = new CurrentAccount(accountNumber);
        accountsList.add(account);
        return account;
    }

    public Account createSavingsAccount(String accountNumber) {
         Account account = new SavingsAccount(accountNumber);
         accountsList.add(account);
         return account;
    }

    public String printDetailClient(String ci) {
        final List<Client> clients = clientList.stream().filter(client -> client.getIdNumber().equals(ci)).collect(Collectors.toList());
        return clients.get(0).print();
    }


    public String printDetailAccount(int id) {
        final List<Account> accounts = accountsList.stream().filter(account -> account.getId() == id).collect(Collectors.toList());
        return accounts.get(0).print();
    }
}
