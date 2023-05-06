package com.javalib9.app.Content;

import com.javalib9.app.Identifier.ISSN;
import com.javalib9.app.Identifier.InvalidIdentifierException;
import java.util.Date;

public class Newspaper extends ISSNContent{
    public Newspaper(String title, String identifierAsString) {
        setTitle(title);
        ISSN identifier = null;
        try {
            identifier = new ISSN(identifierAsString);
        } catch (InvalidIdentifierException e) {
            throw (e);
        }

        setIdentifier(identifier);
        markNotCheckedOut();
    }
    public Newspaper(String title, ISSN identifier) {//defaults to not checked out
        setTitle(title);
        setIdentifier(identifier);
        markNotCheckedOut();
    }
    public Newspaper(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutMemberId();
        clearCheckoutDate();
    }

    public Newspaper(String title, ISSN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutMemberId(checkoutMemberId);
        setCheckoutDate(checkoutDate);
    }
}
