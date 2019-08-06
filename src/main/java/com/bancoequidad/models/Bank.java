package com.bancoequidad.models;
import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    //renplazar por crear cliente y agregar parametros
    public void createClient(Client client) {
        String name = client.getName();
        String id = client.getIdNumber();
        MaritalStatus maritalStatus = client.getMaritalStatus();
        client =  new Client(id,name,maritalStatus);
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

//    public void deposit(String accountNumber, double amount) throws NegativeValuesException, InvalidValuesException {
//        createCurrentAccount(accountNumber).deposit(amount);
//
//    }

    public Client searchClient(String id) {//cambiar y add test
        final Client clients = clientList.stream().filter(client -> client.getIdNumber().equals(id)).findAny().get();
        return  clients;
    }

    public Account searchAccount(String accountNumber) throws Exception {
        final Optional<Account> result = accountsList.stream().filter(account ->account.getAccountNumber().equals(accountNumber)).findFirst();
        if(result.isPresent()){
            return result.get();
        }//make test of ex
        throw new Exception();
    }

    public void assignAccountToTheClient(String accountNumber, String id) {

        createClient(searchClient(accountNumber));
        searchAccount(createSavingsAccount(accountNumber).getAccountNumber());
        getAccountsList().add(createSavingsAccount(accountNumber));
    }
}