package com.javalib9.app.Content;

import java.util.Date;

import com.javalib9.app.Identifier.ISSN;
import com.javalib9.app.Identifier.InvalidIdentifierException;

public class Journal extends ISSNContent{

    public Journal(String title, String identifierAsString) {
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
    public Journal(String title, ISSN identifier) {//defaults to not checked out
        setTitle(title);
        setIdentifier(identifier);
        markNotCheckedOut();
    }
    public Journal(String title, ISSN identifier, String checkoutStatus){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        clearCheckoutDate();
        clearCheckoutMemberId();
    }
    public Journal(String title, ISSN identifier, String checkoutStatus, int checkoutMemberId, Date checkoutDate){
        setTitle(title);
        setIdentifier(identifier);
        setCheckoutStatus(checkoutStatus);
        setCheckoutDate(checkoutDate);
        setCheckoutMemberId(checkoutMemberId);
    }
}
