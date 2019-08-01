package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.RepeatedValuesExeptions;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String idNumber;
    private MaritalStatus maritalStatus;
    private List<Account> accountsList = new ArrayList<>();

    public Client(String name, String idNumber, MaritalStatus marital_status) {
        this.name = name;
        this.idNumber = idNumber;
        this.maritalStatus = marital_status;
    }

    public String getName() {
        return name;
    }


    public String getIdNumber() {
        return idNumber;
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
        String detail = "Name "+this.getName()+" Id Number "+this.getIdNumber()+
                        " Marital Status "+this.getMaritalStatus();//+ "Account number" + getAccountsList()
        return detail;
    }

}
