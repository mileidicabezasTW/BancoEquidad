package com.bancoequidad.models;

import com.bancoequidad.Enum.AccountStatus;
import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

public abstract class Account {
    protected int accountNumber;
    protected int id;
    protected double interest;
    protected double balance;
    protected AccountStatus accountStatus;

    public Account() {
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public int getAccountNumber() {
        return accountNumber;
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

    public abstract void deposit(double depositAmount) throws NegativeValuesException, InvalidValuesException;

    public abstract void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException, InvalidValuesException, InsufficientValuesException;

    public void disable(){
        this.accountStatus = AccountStatus.LOCKED;
    }

    public void enable(){
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public abstract String print();

}

