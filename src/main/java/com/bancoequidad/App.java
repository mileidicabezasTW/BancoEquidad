package com.bancoequidad;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.*;
import com.bancoequidad.models.*;

import java.util.Scanner;

public class App {
    Scanner read = new Scanner(System.in);
    Bank bank = new Bank();

    public void registerClient() {
        int maritalStatusOptions;
        MaritalStatus maritalStatus;
        System.out.println("-----------Enter your detail-----------\n");
        System.out.println("Enter your Id");
        String id = read.next();
        System.out.println("Enter your name");
        String name = read.next();
        System.out.println("Choose your marital status");
        System.out.println("1. Married");
        System.out.println("2. Singled");
        System.out.println("3. Divorced");
        System.out.println("4. Others");
        maritalStatusOptions = read.nextInt();
        if (maritalStatusOptions == 1) {
            maritalStatus = MaritalStatus.MARRIED;
        } else if (maritalStatusOptions == 2) {
            maritalStatus = MaritalStatus.SINGLE;
        } else if (maritalStatusOptions == 3) {
            maritalStatus = MaritalStatus.DIVORCED;
        } else {
            maritalStatus = MaritalStatus.OTHERS;
        }
        bank.createClient(id, name, maritalStatus);
        System.out.println("Successful registration\n");
    }

    public void registerAccount() {
        System.out.println("-----------Enter the account detail-----------\\n");
        System.out.println("Enter the account number");
        String accountNumber = read.next();
        bank.createSavingsAccount(accountNumber);
        System.out.println("Successful registration");
    }

    public void assignAccountToTheClient() throws RepeatedValuesExeptions {
        System.out.println("-----------Enter your detail-----------\n");
        System.out.println("Enter your Id");
        String id = read.next();
        System.out.println("Enter the account number");
        String accountNumber = read.next();
        bank.assignAccountToTheClient(id, accountNumber);
        System.out.println("The account was assign to the client with success");
    }

    public void deposit() throws NegativeValuesException, InvalidValuesException {
        System.out.println("Enter the account number");
        String accountNumber = read.next();
        System.out.println("Enter the deposit amount");
        double amount = read.nextDouble();
        bank.deposit(accountNumber, amount);
        System.out.println("         Successful deposit\n");
        System.out.println("-----------Deposit detail-----------");
        System.out.println("Amount deposit: " + amount + "\nAccount number destination: " + accountNumber + "\nActual balance account deposit destination: " + "\n");
    }

    public void withdrawal() throws InsufficientValuesException, InvalidValuesException, OutRangeValuesException, NegativeValuesException {
        System.out.println("Enter the account number");
        String accountNumber = read.next();
        System.out.println("Enter the withdrawal amount");
        double amountWithdrawal = read.nextDouble();
        bank.withdrawal(accountNumber, amountWithdrawal);
        System.out.println("         Successful deposit\n");
    }
//problems  here
    public void transfer() throws NegativeValuesException, InvalidValuesException, InsufficientValuesException, RepeatedValuesExeptions, OutRangeValuesException {//               //metodo que busque cuentas por medio de numero de cuentas, paratros 1 numero de cuenta, hacer en el banco con pruebas
        System.out.println("Enter the account origin");
        String accountNumberOrigin = read.next();
        System.out.println("Enter the account destination");
        String accountNumberDestination = read.next();
        System.out.println("Enter the transfer amount");
        double transferAmount = read.nextDouble();

        Account accountOrigin = bank.createSavingsAccount(accountNumberDestination);
        Account accountDestination = bank.createSavingsAccount(accountNumberDestination);
        bank.transfer(accountOrigin, accountDestination, transferAmount);
        System.out.println("         Successful transfer\n");
        System.out.println("-----------Transfer Detail-----------");
        System.out.println("Amount transfer: " + transferAmount + "\nAccount number destination: " + accountNumberOrigin + "\nAccount number destination: " +
                accountNumberDestination + "\nActual balance account origin: " + accountOrigin.getBalance() + "\n");
    }

    public void printClientDetail() throws RepeatedValuesExeptions {

        System.out.println("-----------Enter the client detail-----------\n");
        System.out.println("Client id");
        String id = read.next();
        System.out.println("-----------Client detail-----------");
        System.out.println(bank.printDetailClient(id));

    }

    public static void main(String[] args) throws RepeatedValuesExeptions, InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException, OnlyStringException {
        App appBank = new App();
        int option;
        boolean exist = false;
        Scanner read = new Scanner(System.in);

        while (!exist) {
            System.out.println("------------Menu------------");
            System.out.println("1 Register client");
            System.out.println("2 Register account");
            System.out.println("3 Assign account to client");
            System.out.println("4 Make deposit in a account");
            System.out.println("5 Make withdrawal of a account");
            System.out.println("6 Make transfer");
            System.out.println("7 Print a client detail");
            System.out.println("0 Exist");
            System.out.println("Choose an option");
            option = read.nextInt();

            switch (option) {
                case 1:
                    appBank.registerClient();

                    break;
                case 2:
                    appBank.registerAccount();
                    break;
                case 3:
                    appBank.assignAccountToTheClient();
                    break;
                case 4:
                    appBank.deposit();
                    break;
                case 5:
                    appBank.withdrawal();
                    break;
                case 6:
                    appBank.transfer();
                    break;
                case 7:
                    appBank.printClientDetail();
                    break;
                case 0:
                    exist = true;
                    break;
                default:
                    System.out.println("Only number between 0 and 7");
            }
        }
//
//
//
//
//        //hacer un menu con todas las opcion, 1 registrar un cliente, 2 Registrar una cuenta, 3 Asignar cuenta aun cliente, 4  Realizar trnsferencia entre cuentas
//        //5 depositar en una cuenta, 6 Hacer retiro de una cuenta, 7 imprimir detalles de un cliente.
//        //conocer el status por medio del menu,
//
    }
}
