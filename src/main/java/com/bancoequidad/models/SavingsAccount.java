package com.bancoequidad.models;

import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

public class SavingsAccount extends Account{
    final double INTEREST_AMOUNT = 0.0001;

    public SavingsAccount() {
        this.interest = INTEREST_AMOUNT;
    }

    @Override
    public void deposit(double depositAmount) throws NegativeValuesException {
        final double MINIMAL_DEPOSIT_VALUE = 0;
        if (depositAmount < MINIMAL_DEPOSIT_VALUE) {
            throw new NegativeValuesException();
        }
        this.balance = this.balance + depositAmount;
    }

    @Override
    public void withdraw(double withdrawalAmount) throws NegativeValuesException, OutRangeValuesException {
        if (withdrawalAmount < 0) {
            throw new NegativeValuesException();
        } else if (withdrawalAmount > 1000.0) {
            throw new OutRangeValuesException();
        }
        this.balance = withdrawalAmount - this.balance;
    }

    @Override
    public void disable() {
        this.accountStatus = AccountStatus.LOCKED;
    }

    @Override
    public void enable() {
        this.accountStatus = AccountStatus.ACTIVE;
    }

    @Override
    public String print() {
        final String detail = "Id Account " + this.id + " Balance Account " + this.balance
                + " Account Status " + getAccountStatus();
        return detail;
    }
}
