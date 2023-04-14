package src.LibraryFileReader;

public class InvalidCollectionTypeFileWritingException extends RuntimeException{
    InvalidCollectionTypeFileWritingException (String errorMessage){
        super(errorMessage);
    }

    InvalidCollectionTypeFileWritingException (){
        super();
    }

    InvalidCollectionTypeFileWritingException (String errorMessage, Throwable throwableItem){
        super(errorMessage, throwableItem);
    }

    InvalidCollectionTypeFileWritingException (Throwable throwableItem){
        super(throwableItem);
    }
}
