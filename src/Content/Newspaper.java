package src.Content;

import src.Identifier.ISSN;

public class Newspaper extends ISSNContent{
    public Newspaper(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
    }
}
