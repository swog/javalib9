package com.javalib9.app.Identifier;

public class ISSN extends Identifier{

    public ISSN(String id){
        if ( id.length() != 8 ){
            throw new InvalidIdentifierException("Invalid length, ISSN is 8 characters long");
        } else {
            this.setId(id);
        }
    }
}
