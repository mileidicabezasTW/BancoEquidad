package com.bancoequidad;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.models.Bank;
import com.bancoequidad.models.Client;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

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

               break;
           case 2:
               System.out.println("-----------Enter the account detail-----------\\n");
               System.out.println("Enter the account number");
               String accountNumber = read.next();
               bank.createSavingsAccount(accountNumber);
               break;
           case 3:
               int maritalStatusOption;
               MaritalStatus maritalStatusClient;
               System.out.println("-----------Enter your detail-----------\n");
               System.out.println("Enter your Id");
               String idNumber = read.next();
               System.out.println("Enter your name");
               String clientName = read.next();
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
                   maritalStatusClient = MaritalStatus.MARRIED;
               }else if(maritalStatusOptions == 2) {
                   maritalStatusClient = MaritalStatus.SINGLE;
               }else if(maritalStatusOptions == 3) {
                   maritalStatusClient = MaritalStatus.DIVORCED;
               }else{
                   maritalStatusClient = MaritalStatus.OTHERS;
               }
               System.out.println("-----------Enter the account detail-----------\\n");
               System.out.println("Enter the account number");
               String accountOfNumber = read.next();
               bank.createSavingsAccount(accountOfNumber);
               Client client1;
               client1 = new Client(idNumber,clientName,maritalStatusClient);
               bank.addClient(client1);
               client1.addAccount();

               break;
           case 4:
               System.out.println("Has seleccionado la opcion 4");
               break;
           case 5:
               System.out.println("Has seleccionado la opcion 5");
               break;
           case 6:
               System.out.println("Has seleccionado la opcion 6");
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
