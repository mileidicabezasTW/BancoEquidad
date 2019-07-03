package com.bancoequidad.exceptions;

public class NegativeValuesException extends Exception {
    @Override
    public String getMessage() {
        return "Can't deposit negative values";
    }
}
