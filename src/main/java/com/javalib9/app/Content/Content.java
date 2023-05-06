/*
 * A content object as of 4/17/2023 has 5 things:
 * > A title
 * > A checkout status which may only be "Checked Out", "Not Checked Out", or "Lost"
 * > An identifier, specified by the subclasses of Content
 * > A checkout date (null if item is not checked out)
 * > A checkout member id (-1 if item is not checked out)
 *  */

package com.javalib9.app.Content;

import com.javalib9.app.Identifier.Identifier;
import java.util.Date;

public abstract class Content {
    private String title;

    private String checkoutStatus;

    private Date checkoutDate;
    private int checkoutMemberId;

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getCheckoutStatus(){
        return this.checkoutStatus;
    }

    protected void setCheckoutStatus(String newStatus){
        if ( !newStatus.equals("Checked Out") && !newStatus.equals("Not Checked Out") && !newStatus.equals("Lost")){
            throw new InvalidCheckoutStatusException("Checkout status not changed -- must be 'Checked Out', 'Not Checked Out', or 'Lost'");
        }
        else{
            this.checkoutStatus = newStatus;
        }
    }

    public Date getCheckoutDate(){
        return this.checkoutDate;
    }
    protected void setCheckoutDate(Date newDate){
        this.checkoutDate = newDate;
    }
    protected void clearCheckoutDate(){
        this.checkoutDate = null;
    }

    public int getCheckoutMemberId(){
        return this.checkoutMemberId;
    }
    protected void setCheckoutMemberId(int newId){
        if ( newId < 0 && newId != -1){
            throw new InvalidMemberIdException("checkoutMemberId must either be -1 or positive to be valid");
        } else {
            this.checkoutMemberId = newId;
        }
    }
    protected void clearCheckoutMemberId(){
        this.checkoutMemberId = -1;
    }

    public void markCheckedOut(int checkoutMemberId, Date checkoutDate){
        setCheckoutStatus("Checked Out");
        setCheckoutDate(checkoutDate);
        setCheckoutMemberId(checkoutMemberId);
    }
    public void markNotCheckedOut(){
        setCheckoutStatus("Not Checked Out");
        clearCheckoutDate();
        clearCheckoutMemberId();
    }
    public void markLost(){
        setCheckoutStatus("Lost");
    }

    public abstract Identifier getIdentifier();


    @Override
    public String toString(){
        Date checkoutDate = this.getCheckoutDate();

        if ( checkoutDate == null ){
        return this.title.concat(" | ")
            .concat(this.getIdentifier().toString()).concat(" | ")
            .concat(this.getCheckoutStatus()).concat(" | ")
            .concat("N/A").concat(" | ")
            .concat("N/A");

        } else {
        return this.title.concat(" | ")
            .concat(this.getIdentifier().toString()).concat(" | ")
            .concat(this.getCheckoutStatus()).concat(" | ")
            .concat(this.getCheckoutDate().toString()).concat(" | ")
            .concat(String.valueOf(this.getCheckoutMemberId()));

        }
    }
}
