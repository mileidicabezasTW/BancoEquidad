package com.bancoequidad.exceptions;

public class RepeatedValuesExeptions extends Exception{
    @Override
    public String getMessage() {
        return "Repeated values are not allowed";
    }
}
