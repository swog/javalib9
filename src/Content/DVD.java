package src.Content;

import java.util.Date;

import src.Identifier.ISBN;

public class DVD extends ISBNContent{

    public DVD(String title, ISBN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutDate();
        clearCheckoutMemberId();
    }

    public DVD(String title, ISBN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutDate(checkoutDate);
        setCheckoutMemberId(checkoutMemberId);
    }

}
