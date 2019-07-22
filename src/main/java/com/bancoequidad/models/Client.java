package com.bancoequidad.models;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.RepeatedValuesExeptions;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String idNumber;
    private MaritalStatus maritalStatus;
    private List<Account> accountsList = new ArrayList<Account>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public MaritalStatus getMaritalStatus(MaritalStatus maritalStatus) {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
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
                        " Marital Status "+this.getMaritalStatus(maritalStatus.SINGLE);
        return detail;
    }

}
