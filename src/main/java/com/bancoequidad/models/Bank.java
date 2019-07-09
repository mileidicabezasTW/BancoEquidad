package com.bancoequidad.models;

import java.util.ArrayList;
import java.util.List;

public class Bank {


    protected List<Account> accountList = new ArrayList<>();
    protected List<Client> clientList = new ArrayList<>();

    Client client = new Client();

    public List<Client> getClientList() {
        return clientList;
    }

    public void clientList(){
        Client client = new Client();
        client.getIdNumber();
        client.getName();
        client.getMaritalStatus(MaritalStatus.SINGLE);
        clientList.add(client);
    }

}
