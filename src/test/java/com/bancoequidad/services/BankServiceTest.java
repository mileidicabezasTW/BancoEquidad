package com.bancoequidad.services;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.models.Client;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class BankServiceTest {

    BankDomainService bankDomainService;

    @Test
    public void shouldDisplayAllAvailableBankOptions() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        Client mockClient = mock(Client.class);
        MaritalStatus mockMaritalStatus = mock(MaritalStatus.class);
        BankService bankService = new BankService(printStream, mockScanner, mockClient);

        bankService.showMenu();

        verify(printStream).println("1 Register client");
        verify(printStream).println("2 Register account");
        verify(printStream).println("3 Assign account to client");
        verify(printStream).println("4 Make deposit in a account");
        verify(printStream).println("5 Make withdrawal of a account");
        verify(printStream).println("6 Make transfer");
        verify(printStream).println("7 Print a client detail");
        verify(printStream).println("0 Exist");
        verify(printStream).println("Choose an option");
    }

    @Test
    public void shouldEnterInRegistrationClientOption() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        MaritalStatus mockMaritalStatus = mock(MaritalStatus.class);
        Client mockClient = mock(Client.class);
        BankService bankService = new BankService(printStream, mockScanner, mockClient);
        when(mockScanner.nextInt()).thenReturn(1);
        bankService.showMenu();

        verify(printStream).println("-----------Enter the detail-----------\n");
    }

    @Test
    public void shouldDisplayAllAvailableOptionsForInsertClient() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        Client mockClient = mock(Client.class);
        MaritalStatus mockMaritalStatus = mock(MaritalStatus.class);
        BankService bankService = new BankService(printStream, mockScanner, mockClient);

        bankService.showMenuClientRegistration();

        verify(printStream).println("-----------Enter the client detail-----------\n");
        verify(printStream).println("Enter your Id");
        verify(printStream).println("Enter your name");
        verify(printStream).println("Choose your marital status");
        verify(printStream).println("1. Married");
        verify(printStream).println("2. Singled");
        verify(printStream).println("3. Divorced");
        verify(printStream).println("4. Others");
    }

    @Test
    public void shouldChooseTheClientMaritalStatus() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        Client mockClient = mock(Client.class);
        BankService bankService = new BankService(printStream, mockScanner, mockClient);
        Mockito.doReturn(MaritalStatus.MARRIED).when(mockScanner.nextInt()).equals(1);
        Mockito.doReturn(MaritalStatus.MARRIED).when(mockScanner.nextInt());

        bankService.chooseMaritalStatus();

        verify(printStream).println(MaritalStatus.MARRIED);

    }
}
