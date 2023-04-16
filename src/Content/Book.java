package src.Content;

import src.Identifier.ISBN;
import java.util.Date;

public class Book extends ISBNContent{

    public Book(String title, ISBN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutDate();
        clearCheckoutMemberId();
    }

    public Book(String title, ISBN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutDate(checkoutDate);
        setCheckoutMemberId(checkoutMemberId);
    }

}
