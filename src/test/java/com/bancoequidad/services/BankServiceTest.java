package com.bancoequidad.services;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class BankServiceTest {

    @Test
    public void shouldDisplayAllAvailableBankOptions() {
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        BankService bankService = new BankService(printStream, mockScanner);

        bankService.showMenu();

        verify(printStream).println("1 Register client");
        verify(printStream).println("2 Register account");
    }


    @Test
    public void shouldRegisterClientWhenOption1IsInserted(){
        PrintStream printStream = mock(PrintStream.class);
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);
        BankService bankService = new BankService(printStream, mockScanner);

        bankService.showMenu();

        verify(printStream).println("-----------Enter your detail-----------\n");
    }
}
