package src.LibraryFileReader;

/*
 * This exception is thrown when a file is attempted to be read into a collection except the file attempted to be read is improperly formatted.
 * This means either that Trey wrote a writeIntoFile method wrong (very possible) or that someone modified the file by hand without adhering to the rules of content list files.
 *  */

public class InvalidContentFileException extends RuntimeException{
    InvalidContentFileException (String errorMessage){
        super(errorMessage);
    }

    InvalidContentFileException (){
        super();
    }

    InvalidContentFileException (String errorMessage, Throwable throwableItem){
        super(errorMessage, throwableItem);
    }

    InvalidContentFileException (Throwable throwableItem){
        super(throwableItem);
    }
}
