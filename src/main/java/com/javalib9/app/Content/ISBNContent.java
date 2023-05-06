package com.javalib9.app.Content;

import com.javalib9.app.Identifier.Identifier;
import com.javalib9.app.Identifier.ISBN;

public abstract class ISBNContent extends Content implements Comparable<ISBNContent>{
    ISBN id;


    public ISBN getISBN(){
        return this.id;
    }

    @Override
    public Identifier getIdentifier(){
        return this.id;
    }

    public void setIdentifier(ISBN identifier){
        this.id = identifier;
    }

    public int compareTo(ISBNContent otherContent){
        return this.getISBN().compareTo(otherContent.getISBN());
    }
}
