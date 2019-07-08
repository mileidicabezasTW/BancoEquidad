package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

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

    public void setInterest(){
        this.interest = interest;
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

    public void deposit(double depositAmount) throws NegativeValuesException {
        final double MINIMAL_DEPOSIT_VALUE = 0;
        if (depositAmount < MINIMAL_DEPOSIT_VALUE) {
            throw new NegativeValuesException();
        }
        this.balance = this.balance + depositAmount;
    }

    public void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException {
        final double MINIMAL_WITHDRAWAL_VALUE = 0;
        final double MAXIMAL_WITHDRAWAL_VALUE = 3000.0;

        if (withdrawalAmount < MINIMAL_WITHDRAWAL_VALUE) {
            throw new NegativeValuesException();
        } else if (withdrawalAmount > MAXIMAL_WITHDRAWAL_VALUE) {
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
