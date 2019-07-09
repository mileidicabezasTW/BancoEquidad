package com.bancoequidad.models;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    Account savingsAccount = new SavingsAccount();
    Account currentAccount = new CurrentAccount();
    Client client = new Client();

    protected List<Account> accountList = new ArrayList<>();
    protected List<Client> clientList = new ArrayList<>();

    public List<Client> getClientList() {
        return clientList;
    }

    public void clientList(){

        client.getIdNumber();
        client.getName();
        client.getMaritalStatus(MaritalStatus.SINGLE);
        clientList.add(client);
    }

}
