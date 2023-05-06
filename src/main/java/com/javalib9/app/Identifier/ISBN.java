package com.javalib9.app.Identifier;

public class ISBN extends Identifier{ //only accepts 10 digits (ISBN-10)

    public ISBN(String id){
        if ( id.length() != 10 ){
            throw new InvalidIdentifierException("Invalid length, ISBN is 10 characters long");
        } else {
            this.setId(id);
        }
    }

}
