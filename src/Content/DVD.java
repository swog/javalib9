package src.Content;

import src.Identifier.ISBN;

public class DVD extends ISBNContent{

    DVD(String title, ISBN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
    }

}
