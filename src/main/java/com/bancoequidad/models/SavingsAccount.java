package com.bancoequidad.models;

import com.bancoequidad.exceptions.InsufficientValuesException;
import com.bancoequidad.exceptions.InvalidValuesException;
import com.bancoequidad.exceptions.NegativeValuesException;
import com.bancoequidad.exceptions.OutRangeValuesException;

public class SavingsAccount extends Account{
    final double INTEREST_AMOUNT = 0.0001;

    public SavingsAccount(String accountNumber) {
        this.interest = INTEREST_AMOUNT;
        this.accountNumber = accountNumber;

    }

    @Override
    public void deposit(double depositAmount) throws NegativeValuesException, InvalidValuesException {
        final double MINIMAL_DEPOSIT_VALUE = 0;
        if (depositAmount < MINIMAL_DEPOSIT_VALUE) {
            throw new NegativeValuesException();
        }
        if(depositAmount == MINIMAL_DEPOSIT_VALUE){
            throw new InvalidValuesException();
        }
        this.balance = this.balance + depositAmount;
    }

    @Override
    public void withdraw(double withdrawalAmount) throws OutRangeValuesException, InvalidValuesException, NegativeValuesException, InsufficientValuesException {
        final double MINIMAL_WITHDRAWAL_VALUE = 0;
        final double MAXIMAL_WITHDRAWAL_VALUE = 1000.0;

         if (withdrawalAmount > MAXIMAL_WITHDRAWAL_VALUE) {
            throw new OutRangeValuesException();
        }
         if(withdrawalAmount < MINIMAL_WITHDRAWAL_VALUE){
             throw new NegativeValuesException();
         }
        if(withdrawalAmount == MINIMAL_WITHDRAWAL_VALUE){
            throw new InvalidValuesException();
        }
        if(this.balance < withdrawalAmount){
            throw new InsufficientValuesException();
        }
        this.balance = this.balance - withdrawalAmount;
    }

    @Override
    public String print() {
        final String detail = "Id Account " + getId() + " Balance Account " + this.balance
                + " Account Status " + getAccountStatus();
        return detail;
    }
}
