package src.Content;

import src.Identifier.ISBN;

public class DVD extends ISBNContent{

    DVD(String title, ISBN identifier){
        setTitle(title);
        setIdentifier(identifier);
    }

}
