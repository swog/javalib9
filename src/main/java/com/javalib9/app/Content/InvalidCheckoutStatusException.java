package com.javalib9.app.Content;

public class InvalidCheckoutStatusException extends RuntimeException{
    InvalidCheckoutStatusException(String errorMessage){
        super(errorMessage);
    }
    InvalidCheckoutStatusException(){
        super();
    }
    InvalidCheckoutStatusException(String errorMessage, Throwable throwableItem){

        super(errorMessage, throwableItem);
    } 
    InvalidCheckoutStatusException (Throwable throwableItem){

        super(throwableItem);
    }
}
