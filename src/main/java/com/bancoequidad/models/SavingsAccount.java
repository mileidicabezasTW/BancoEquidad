package com.bancoequidad.models;

public class SavingsAccount {

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
}
