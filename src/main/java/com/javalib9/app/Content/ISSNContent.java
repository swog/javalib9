package com.javalib9.app.Content;

import com.javalib9.app.Identifier.ISSN;
import com.javalib9.app.Identifier.Identifier;

public abstract class ISSNContent extends Content implements Comparable<ISSNContent>{
    ISSN id;


    public ISSN getISSN(){
        return this.id;
    }

    @Override
    public Identifier getIdentifier(){
        return this.id;
    }

    public void setIdentifier(ISSN identifier){
        this.id = identifier;
    }

    public int compareTo(ISSNContent otherContent){
        return this.getISSN().compareTo(otherContent.getISSN());
    }
}
