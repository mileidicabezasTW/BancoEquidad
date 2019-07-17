package com.bancoequidad.exceptions;

public class InsufficientValuesException extends Exception {
    @Override
    public String getMessage() {
        return "Can´t make withdrawal, insufficient Balance in your account ";
    }
}
