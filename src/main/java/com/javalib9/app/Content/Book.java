package com.javalib9.app.Content;

import com.javalib9.app.Identifier.ISBN;
import com.javalib9.app.Identifier.InvalidIdentifierException;
import java.util.Date;

public class Book extends ISBNContent{

    public Book (String title, String identifierAsString) {
        setTitle(title);
        ISBN identifier = null;
        try {
            identifier = new ISBN(identifierAsString);
        } catch (InvalidIdentifierException e) {
            throw (e);
        }

        setIdentifier(identifier);
        markNotCheckedOut();
    }
    public Book(String title, ISBN identifier) {//defaults to not checked out
        setTitle(title);
        setIdentifier(identifier);
        markNotCheckedOut();
    }
    public Book(String title, ISBN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutDate();
        clearCheckoutMemberId();
    }

    public Book(String title, ISBN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutDate(checkoutDate);
        setCheckoutMemberId(checkoutMemberId);
    }

}
