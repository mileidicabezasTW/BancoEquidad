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
        if (depositAmount < 0) {
            throw new NegativeValuesException();
        }
        this.balance = this.balance + depositAmount;
    }

    public void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException {
        if (withdrawalAmount < 0) {
            throw new NegativeValuesException();
        }else if(withdrawalAmount > 2000.0){
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

    public double makeDeposit() throws NegativeValuesException {
        final double amount = 0.0;
        deposit(amount);
        return  amount;
    }

    public double makeWithdrawal() throws NegativeValuesException, OutRangeValuesException {
        final double amount = 0.0;
        withdraw(amount);
        return  amount;
    }
}
