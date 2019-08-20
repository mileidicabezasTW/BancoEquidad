package com.bancoequidad.services;

import com.bancoequidad.Enum.MaritalStatus;
import com.bancoequidad.models.Client;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class BankServiceTest {
    BankDomainService bankDomainService;

    @Test
    public void shouldDisplayAllAvailableBankOptions() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        BankService bankService = new BankService(printStream, mockScanner, mockBankDomainService);

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
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        BankService bankService = new BankService(printStream, mockScanner, mockBankDomainService);
        when(mockScanner.nextInt()).thenReturn(1);
        bankService.showMenu();

        verify(printStream).println("-----------Enter the detail-----------\n");
    }


    @Test
    public void shouldEnterTheClientId() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn("12345");

        BankService bankService = new BankService(printStream, mockScanner, mockBankDomainService);
        bankService.showMenu();

        verify(printStream).println("Enter your name");
    }


    @Test
    public void shouldEnterTheClientName() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn("marc");

        BankService bankService = new BankService(printStream, mockScanner, mockBankDomainService);
        bankService.showMenu();

        verify(printStream).println("Choose your marital status");
    }


    @Test
    public void shouldChooseTheMaritalStatus(){
        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.nextInt()).thenReturn(1);

        BankService bankService = new BankService(mockPrintStream,mockScanner,mockBankDomainService);
        bankService.showMenu();

        verify(mockPrintStream).println("Successful registration\n");

    }

    @Test
    public void shouldRegisterAClient(){
        final String ID = "111";
        final String NAME = "marc";
        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn(ID);

        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn(NAME);

        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.nextInt()).thenReturn(3);

        BankService bankService = new BankService(mockPrintStream,mockScanner,mockBankDomainService);
        bankService.showMenu();
        mockBankDomainService.createClient(ID,NAME,MaritalStatus.MARRIED);
        verify(mockPrintStream).println("Successful registration\n");
    }
}
