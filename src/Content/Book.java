package src.Content;

import src.Identifier.ISBN;

public class Book extends ISBNContent{

    Book(String title, ISBN identifier){
        setTitle(title);
        setIdentifier(identifier);
    }

}
