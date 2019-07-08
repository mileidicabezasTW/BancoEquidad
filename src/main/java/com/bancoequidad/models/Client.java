package com.bancoequidad.models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String idNumber;
    private MaritalStatus maritalStatus;
    List<Account> clientAccount = new ArrayList<>();


    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public MaritalStatus getMaritalStatus(MaritalStatus maritalStatus) {
        return maritalStatus;
    }

    public String print() {
        String detail = "Name "+this.getName()+" Id Number "+this.getIdNumber()+
                        " Marital Status "+this.getMaritalStatus(maritalStatus.SINGLE);
        return detail;
    }
}
