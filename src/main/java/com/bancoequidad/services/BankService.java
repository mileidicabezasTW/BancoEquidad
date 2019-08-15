package com.bancoequidad.services;

import com.bancoequidad.models.Client;

import java.io.PrintStream;
import java.util.Scanner;

public class BankService {

    private PrintStream printStream;
    private Scanner scanner;

    public BankService(PrintStream printStream, Scanner scanner) {
        this.printStream = printStream;
        this.scanner = scanner;
    }

    public void showMenu() {
        printStream.println("------------Menu------------");
        printStream.println("1 Register client");
        printStream.println("2 Register account");
        printStream.println("3 Assign account to client");
        printStream.println("4 Make deposit in a account");
        printStream.println("5 Make withdrawal of a account");
        printStream.println("6 Make transfer");
        printStream.println("7 Print a client detail");
        printStream.println("0 Exist");
        printStream.println("Choose an option");

        int option = scanner.nextInt();

        if(option == 1){
            printStream.println("-----------Enter your detail-----------\n");
        }

    }


}
