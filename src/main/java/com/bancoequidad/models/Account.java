package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

public class Account {

    private int id;
    private final double INTEREST;
    private double balance;
    private AccountStatus accountStatus;

    public Account() {
        this.accountStatus = AccountStatus.ACTIVE;
        this.INTEREST = 0.00015;
    }

    public int getId() {
        return id;
    }

    public double getINTEREST() {
        return INTEREST;
    }

    public double getBalance() {
        return balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void deposit(double depositAmount) throws NegativeValuesException {
        if (depositAmount < 0) {
            throw new NegativeValuesException();
        }
        this.balance = this.balance + depositAmount;
    }

    public void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException {
        if (withdrawalAmount < 0) {
            throw new NegativeValuesException();
        } else if (withdrawalAmount > 2000.0) {
            throw new OutRangeValuesException();
        }
        this.balance = withdrawalAmount - this.balance;
    }

    public void disable() {
        this.accountStatus = AccountStatus.LOCKED;
    }

    public void enable() {
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public String print() {
        final String detail = "Id Account " + this.id + " Balance Account " + this.balance
                + " Account Status " + getAccountStatus();
        return detail;
    }


}
