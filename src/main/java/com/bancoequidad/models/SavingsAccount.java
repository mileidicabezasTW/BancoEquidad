package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

public class SavingsAccount{

    private int id;
    private double interest;
    public double balance;
    public AccountStatus accountStatus;

    public SavingsAccount() {
        this.accountStatus = AccountStatus.ACTIVE;
        this.interest = 0.0001;
    }

    public int getId() {
        return id;
    }

    public double getInterest() {
        return this.interest;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException {
        if (withdrawalAmount < 0) {
            throw new NegativeValuesException();
        } else if (withdrawalAmount > 2000.0) {
            throw new OutRangeValuesException();
        }
        this.balance = withdrawalAmount - this.balance;
    }
}
