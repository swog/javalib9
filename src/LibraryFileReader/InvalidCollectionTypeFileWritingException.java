package src.LibraryFileReader;

/*
 * Throw this exception when the user attempts to write a collection to a file where at least one item in the collection has the incorrect type for the write function called
 * Remember, if you want to write a collection to a file where the collection has mismatched content items, use the catchall writing function
 *  */

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
