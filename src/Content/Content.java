/*
 * A content object as of 4/14/2023 has 3 things:
 * > A title
 * > A checkout status which may only be "Checked Out", "Not Checked Out", or "Lost"
 * > An identifier, specified by the subclasses of Content
 *  */

package src.Content;
import src.Identifier.Identifier;

public abstract class Content {
    private String title;

    private String checkoutStatus;

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getCheckoutStatus(){
        return this.checkoutStatus;
    }

    public void setCheckoutStatus(String newStatus){
        if ( !newStatus.equals("Checked Out") && !newStatus.equals("Not Checked Out") && !newStatus.equals("Lost")){
            throw new InvalidCheckoutStatusException("Checkout status not changed -- must be 'Checked Out', 'Not Checked Out', or 'Lost'");
        }
        else{
            this.checkoutStatus = newStatus;
        }
    }

    public abstract Identifier getIdentifier();
}
