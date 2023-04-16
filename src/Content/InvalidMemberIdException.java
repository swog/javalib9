package src.Content;

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
