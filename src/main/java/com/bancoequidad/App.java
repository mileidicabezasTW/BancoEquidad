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
               System.out.println("Has seleccionado la opcion 1");

               break;
           case 2:
               System.out.println("Has seleccionado la opcion 2");
               break;
           case 3:
               System.out.println("Has seleccionado la opcion 3");
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
