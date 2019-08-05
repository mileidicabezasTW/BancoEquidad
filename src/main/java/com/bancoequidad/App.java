package com.bancoequidad;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.exceptions.*;
import com.bancoequidad.models.*;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RepeatedValuesExeptions, InvalidValuesException, NegativeValuesException, OutRangeValuesException, InsufficientValuesException {

        Bank bank = new Bank();
        Scanner read = new Scanner(System.in);
        boolean exist = false;
        int option;
        while(!exist){
        System.out.println("------------Menu------------");
        System.out.println("1 Register client");
        System.out.println("2 Register account");
        System.out.println("3 Assign account to client");
        System.out.println("4 Make transfer");
        System.out.println("5 Make deposit in a account");
        System.out.println("6 Make withdrawal of a account");
        System.out.println("7 Print a client detail");
        System.out.println("0 Exist");

        System.out.println("Choose an option");
        option = read.nextInt();

       switch (option){
           case 1:
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
               if (maritalStatusOptions > 4){
                   System.out.println("This option is not available");
               }
               if(maritalStatusOptions == 1) {
                   maritalStatus = MaritalStatus.MARRIED;
               }else if(maritalStatusOptions == 2) {
                   maritalStatus = MaritalStatus.SINGLE;
               }else if(maritalStatusOptions == 3) {
                   maritalStatus = MaritalStatus.DIVORCED;
               }else{
                   maritalStatus = MaritalStatus.OTHERS;
               }
               Client client = new Client(id, name,maritalStatus);
               bank.addClient(client);
               System.out.println("Successful registration\n");
               System.out.println("-----------Client detail-----------");
               System.out.println(client.print());
               break;
           case 2:
               System.out.println("-----------Enter the account detail-----------\\n");
               System.out.println("Enter the account number");
               String accountNumber = read.next();
               bank.createSavingsAccount(accountNumber);
               System.out.println("Successful registration");
               System.out.println("-----------Account detail-----------");

               break;
           case 3:
               MaritalStatus maritalStatusClient;
               System.out.println("-----------Enter the client detail-----------\n");
               System.out.println("Client id");
               String idNumber = read.next();
               System.out.println("Client name");
               String clientName = read.next();
               System.out.println("Choose Client marital status");
               System.out.println("1. Married");
               System.out.println("2. Singled");
               System.out.println("3. Divorced");
               System.out.println("4. Others");
               maritalStatusOptions = read.nextInt();
               if (maritalStatusOptions > 4){
                   System.out.println("This option is not available");
               }
               if(maritalStatusOptions == 1) {
                   maritalStatusClient = MaritalStatus.MARRIED;
               }else if(maritalStatusOptions == 2) {
                   maritalStatusClient = MaritalStatus.SINGLE;
               }else if(maritalStatusOptions == 3) {
                   maritalStatusClient = MaritalStatus.DIVORCED;
               }else{
                   maritalStatusClient = MaritalStatus.OTHERS;
               }
               System.out.println("Enter the account number");
               String accountOfNumber = read.next();
               bank.createSavingsAccount(accountOfNumber);
               Client client1;
               client1 = new Client(idNumber,clientName,maritalStatusClient);
               client1.getIdNumber();
               client1.getName();
               client1.getMaritalStatus();
               client1.getAccountsList();
               bank.addClient(client1);
               Account account1 = new SavingsAccount(accountOfNumber);
               client1.addAccount(account1);
               System.out.println("      Successful assignment\n" );
               System.out.println("-----------Client detail-----------");
               System.out.println("ID: "+client1.getIdNumber()+"\nName: "+client1.getName()+"\nMarital status: "+
                                   client1.getMaritalStatus()+"\nAccount number: "+client1.getAccountsList());
               break;
           case 4:
               String accountNumberOrigin = "1232122233";
               String accountNumberDestination = "1232122433";
               Account accountOrigin = new CurrentAccount(accountNumberOrigin);
               Account accountDestination = new SavingsAccount(accountNumberDestination);
               double amountDeposit = 50.98;
               double transferAmount = 0.0;
               System.out.println("Enter the account destination");
               accountNumberDestination  = read.next();
               System.out.println("Enter the transfer amount");
               transferAmount = read.nextDouble();
               accountOrigin.deposit(amountDeposit);
               bank.transfer(accountOrigin,accountDestination,transferAmount);
               System.out.println("         Successful transfer\n");
               System.out.println("-----------Transfer Detail-----------");
               System.out.println("Amount transfer: "+transferAmount +"\nAccount number destination: "+
                                  accountNumberDestination+ "\nActual balance account origin: "+ accountOrigin.getBalance()+"\n");
               break;
           case 5:
               String number = "1232122233";
               Account accountOne = new CurrentAccount(number);
               double amount = 0.0;
               System.out.println("Enter the account destination");
               accountNumberDestination  = read.next();
               System.out.println("Enter the deposit amount");
               amount = read.nextDouble();
               accountOne.deposit(amount);
               System.out.println("         Successful deposit\n");
               System.out.println("-----------Deposit detail-----------");
               System.out.println("Amount deposit: "+amount+"\nAccount number destination: "+
                       accountOne.getAccountNumber()+ "\nActual balance account deposit destination: "+ accountOne.getBalance()+"\n");
               break;
           case 6:
               String AccountNumberOne = "1232122233";
               Account accountTwo = new CurrentAccount(AccountNumberOne);
               double depositAmount = 55.98;
               double amountWithdrawal = 0.0;
               accountTwo.deposit(depositAmount);
               System.out.println("Enter the account destination");
               AccountNumberOne  = read.next();
               System.out.println("Enter the withdrawal amount");
               amountWithdrawal = read.nextDouble();
               accountTwo.withdraw(amountWithdrawal);
               System.out.println("         Successful deposit\n");
               System.out.println("-----------Withdrawal detail-----------");
               System.out.println("Amount withdrawal: "+amountWithdrawal+"\nAccount number destination: "+
                       accountTwo.getAccountNumber()+ "\nActual balance in account: "+ accountTwo.getBalance()+"\n");
               break;
           case 7:
               System.out.println("Has seleccionado la opcion 7");
               break;
           case 0:
               exist = true;
               break;
           default:
               System.out.println("Solo números entre 0 y 7");
       }
    }




        //hacer un menu con todas las opcion, 1 registrar un cliente, 2 Registrar una cuenta, 3 Asignar cuenta aun cliente, 4  Realizar trnsferencia entre cuentas
        //5 depositar en una cuenta, 6 Hacer retiro de una cuenta, 7 imprimir detalles de un cliente.
        //conocer el status por medio del menu,

    }
}
