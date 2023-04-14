package src.Content;

import src.Identifier.ISBN;

public class Book extends ISBNContent{

    public Book(String title, ISBN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
    }

}
