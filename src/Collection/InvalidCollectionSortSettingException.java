package src.Collection;

public class InvalidCollectionSortSettingException extends RuntimeException{
    InvalidCollectionSortSettingException (String errorMessage){
        super(errorMessage);
    }
    InvalidCollectionSortSettingException (){
        super();
    }
    InvalidCollectionSortSettingException (String errorMessage, Throwable throwableItem){

        super(errorMessage, throwableItem);
    }
    InvalidCollectionSortSettingException (Throwable throwableItem){

        super(throwableItem);
    }
}
