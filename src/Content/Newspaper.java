package src.Content;

import src.Identifier.ISSN;
import java.util.Date;

public class Newspaper extends ISSNContent{
    public Newspaper(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutMemberId();
        clearCheckoutDate();
    }

    public Newspaper(String title, ISSN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutMemberId(checkoutMemberId);
        setCheckoutDate(checkoutDate);
    }
}
