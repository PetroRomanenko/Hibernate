package com.ferros.exeptions;

public class NoDataInDatabaseException extends Exception{
    public NoDataInDatabaseException(String message) {
        super(message);
    }
}
