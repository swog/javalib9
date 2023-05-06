package com.javalib9.app.Content;

public class InvalidMemberIdException extends RuntimeException{
    InvalidMemberIdException(String errorMessage){
        super(errorMessage);
    }
    InvalidMemberIdException(){
        super();
    }
    InvalidMemberIdException(String errorMessage, Throwable throwableItem){

        super(errorMessage, throwableItem);
    }
    InvalidMemberIdException (Throwable throwableItem){

        super(throwableItem);
    }
}
