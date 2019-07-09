package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

public abstract class Account {
    protected int id;
    protected double interest;
    protected double balance;
    protected AccountStatus accountStatus;

    public Account() {
        this.accountStatus = AccountStatus.ACTIVE;
        this.interest = interest;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public abstract void deposit(double depositAmount) throws NegativeValuesException;

    public abstract void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException;

    public abstract void disable();

    public abstract void enable();

    public abstract String print();

}

