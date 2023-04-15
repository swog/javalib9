package src.LibraryFileReader;

/*
 * This exception is thrown when a collection is attempted to be written to a file and
 * the content in the file is not a DVD, Newspaper, Book, or Journal
 *
 * I don't know why this would ever be thrown. If you find yourself here I'm sorry.
 *  */

public class InvalidCollectionException extends RuntimeException{
    InvalidCollectionException (String errorMessage){
        super(errorMessage);
    }

    InvalidCollectionException (){
        super();
    }

    InvalidCollectionException (String errorMessage, Throwable throwableItem){
        super(errorMessage, throwableItem);
    }

    InvalidCollectionException (Throwable throwableItem){
        super(throwableItem);
    }
}
