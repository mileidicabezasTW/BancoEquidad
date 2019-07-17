package com.bancoequidad.exceptions;

public class InvalidValuesException extends Exception{
    @Override
    public String getMessage() {
        return "values equal to zero are not allowed";
    }
}
