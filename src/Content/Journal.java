package src.Content;

import java.util.Date;

import src.Identifier.ISSN;

public class Journal extends ISSNContent{

    public Journal(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutDate();
        clearCheckoutMemberId();
    }
    public Journal(String title, ISSN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutDate(checkoutDate);
        setCheckoutMemberId(checkoutMemberId);
    }
}
