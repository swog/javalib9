package com.javalib9.app.Identifier;

public class InvalidIdentifierException extends RuntimeException{
    public InvalidIdentifierException(String errorMessage){
        super(errorMessage);
    }

    public InvalidIdentifierException(){
        super();
    }

    public InvalidIdentifierException(String errorMessage, Throwable throwableItem){
        super(errorMessage, throwableItem);
    }

    public InvalidIdentifierException(Throwable throwableItem){
        super(throwableItem);
    }
}
