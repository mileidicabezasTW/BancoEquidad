package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;

public class CurrentAccount {

    private int id;
    private double interest;
    private double balance;
    private AccountStatus accountStatus;

    public CurrentAccount() {
        this.accountStatus = AccountStatus.ACTIVE;
        this.interest = 0.00015;
    }

    public int getId() {
        return id;
    }

    public double getInterest() {
        return interest;
    }

    public double getBalance() {
        return balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void deposit(double amount) throws NegativeValuesException {
        if(amount < 0){
            throw new NegativeValuesException();
        }

    }
}
