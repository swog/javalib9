package src.Content;

import src.Identifier.ISSN;

public class Newspaper extends ISSNContent{
    Newspaper(String title, ISSN identifier){
        setTitle(title);
        setIdentifier(identifier);
    }
}
