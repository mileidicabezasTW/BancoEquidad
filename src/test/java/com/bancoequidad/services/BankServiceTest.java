package com.bancoequidad.services;

import com.bancoequidad.Enum.MaritalStatus;
import org.junit.Test;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;
import static org.mockito.Mockito.*;

public class BankServiceTest {

    @Test
    public void shouldDisplayAllAvailableBankOptions() {
        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        BankService bankService = new BankService(mockPrintStream, mockScanner, mockBankDomainService);

        bankService.showMenu();

        verify(mockPrintStream).println("1 Register client");
        verify(mockPrintStream).println("2 Register account");
        verify(mockPrintStream).println("3 Assign account to client");
        verify(mockPrintStream).println("4 Make deposit in a account");
        verify(mockPrintStream).println("5 Make withdrawal of a account");
        verify(mockPrintStream).println("6 Make transfer");
        verify(mockPrintStream).println("7 Print a client detail");
        verify(mockPrintStream).println("0 Exist");
        verify(mockPrintStream).println("Choose an option");
    }

    @Test
    public void shouldEnterInRegistrationClientOption() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);

        BankService bankService = new BankService(printStream, mockScanner, mockBankDomainService);
        bankService.showMenu();

        verify(printStream).println("-----------Enter the client detail-----------\n");
    }


    @Test
    public void shouldEnterTheClientId() {
        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn("12345");

        BankService bankService = new BankService(mockPrintStream, mockScanner, mockBankDomainService);
        bankService.showMenu();

        verify(mockPrintStream).println("Enter your name");
    }


    @Test
    public void shouldEnterTheClientName() {
        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn("marc");

        BankService bankService = new BankService(mockPrintStream, mockScanner, mockBankDomainService);
        bankService.showMenu();

        verify(mockPrintStream).println("Choose your marital status");
    }

    @Test
    public void shouldChooseTheMaritalStatus(){
        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
       // when(mockScanner.nextInt()).thenReturn(1).thenReturn();


        BankService bankService = new BankService(mockPrintStream,mockScanner,mockBankDomainService);

        bankService.showMenu();

        verify(mockPrintStream).println("Successful .registration\n");
    }

    @Test
    public void shouldRegisterAClient(){
        final String ID = "111";
        final String NAME = "marc";
        MaritalStatus maritalStatus = MaritalStatus.DIVORCED;

        PrintStream mockPrintStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankDomainService mockBankDomainService = mock(BankDomainService.class);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn(ID);
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.next()).thenReturn(NAME);
        when(mockScanner.nextInt()).thenReturn(1).thenReturn(3);
        BankService bankService = new BankService(mockPrintStream,mockScanner,mockBankDomainService);
        bankService.showMenu();

        verify(mockPrintStream).println("2 Register account");
    }
}
