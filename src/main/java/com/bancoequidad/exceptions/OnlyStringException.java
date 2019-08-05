package com.bancoequidad.exceptions;

public class OnlyStringException extends Exception {
    @Override
    public String getMessage() {
        return "Please just enter characters";
    }
}
