package com.bancoequidad.services;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.models.Account;

import java.io.PrintStream;
import java.util.Scanner;

public class BankService {
    private PrintStream printStream;
    private Scanner scanner;
    private BankDomainService bankDomainService;

    public BankService(PrintStream printStream, Scanner scanner, BankDomainService bankDomainService) {
        this.printStream = printStream;
        this.scanner = scanner;
        this.bankDomainService = bankDomainService;
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

        if (option == 1) {
            showMenuClientRegistration();

        }
    }

    private void showMenuClientRegistration() {
        int maritalStatusOptions;
        MaritalStatus maritalStatus = MaritalStatus.OTHERS;
        System.out.println("-----------Enter your detail-----------\n");
        System.out.println("Enter your Id");
        String id = scanner.next();
        System.out.println("Enter your name");
        String name = scanner.next();
        System.out.println("Choose your marital status");
        System.out.println("1. Married");
        System.out.println("2. Singled");
        System.out.println("3. Divorced");
        System.out.println("4. Others");
        maritalStatusOptions = scanner.nextInt();

        if (maritalStatusOptions == 1) {
            maritalStatus = MaritalStatus.MARRIED;
        } else if (maritalStatusOptions == 2) {
            maritalStatus = MaritalStatus.SINGLE;
        }else if (maritalStatusOptions == 3) {
            maritalStatus = MaritalStatus.DIVORCED;
        } else if(maritalStatusOptions >= 4){
            maritalStatus = MaritalStatus.OTHERS;
        }
        printStream.println("Successful registration\n");
    }
}

