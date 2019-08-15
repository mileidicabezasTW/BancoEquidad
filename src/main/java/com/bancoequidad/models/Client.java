package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.RepeatedValuesExeptions;

import java.util.ArrayList;
import java.util.List;

public class Client {
    Account account;
    private String idNumber;
    private String name;
    private MaritalStatus maritalStatus;
    private List<Account> accountsList = new ArrayList<>();

    public Client( String idNumber,String name, MaritalStatus marital_status) {
        this.idNumber = idNumber;
        this.name = name;
        this.maritalStatus = marital_status;
    }
    public String getIdNumber() {
        return idNumber;
    }
    public String getName() {
        return name;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public void addAccount(Account account) throws RepeatedValuesExeptions {

      if(accountsList.contains(account)){
          throw new RepeatedValuesExeptions();
       }
        this.accountsList.add(account);
    }

    public String print() {
        String detail = "Id Number: "+this.getIdNumber()+"\nName: "+this.getName()+
                        "\nMarital Status: "+this.getMaritalStatus()+ "\nAccount number: " + getAccountsList();
        return detail;
    }

}
