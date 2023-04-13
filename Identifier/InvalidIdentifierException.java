public class InvalidIdentifierException extends RuntimeException{
    InvalidIdentifierException(String errorMessage){
        super(errorMessage);
    }

    InvalidIdentifierException(){
        super();
    }

    InvalidIdentifierException(String errorMessage, Throwable throwableItem){
        super(errorMessage, throwableItem);
    }

    InvalidIdentifierException(Throwable throwableItem){
        super(throwableItem);
    }
}
