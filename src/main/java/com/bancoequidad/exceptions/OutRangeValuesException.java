package com.bancoequidad.exceptions;

public class OutRangeValuesException extends Exception{
    @Override
    public String getMessage() {
        return "Can't exceed the maximum range  to withdraw";
    }
}
