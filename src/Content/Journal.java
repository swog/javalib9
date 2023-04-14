package src.Content;

import src.Identifier.ISSN;

public class Journal extends ISSNContent{

    public Journal(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
    }
}
