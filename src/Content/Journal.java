package src.Content;

import src.Identifier.ISSN;

public class Journal extends ISSNContent{

    Journal(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
    }
}
