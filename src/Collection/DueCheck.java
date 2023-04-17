package src.Collection;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import src.Collection.Collection;
import src.Content.*;
import src.Person.*;


public class DueCheck {


    
    public DueCheck()
    {

    }
    public void UpdateCheckedCollection()
    {
        Collection library = new Collection("CheckoutCollection");
        Collection CheckedOutCollection = library.getCheckedOutCollectionSubset("CheckedOutCollection");
        Content [] ContentArray = CheckedOutCollection.getContentArray();
        for ( int i = 0 ; i < ContentArray.length ; i++ ){
            Date d1= new Date();
            Date d2 = ContentArray[i].getCheckoutDate();
            long diffInMillies = Math.abs(d2.getTime() - d1.getTime()); //Gets the number of milliseconds since the content has been checked out
            int diffInDays =  (int) (diffInMillies / (1000*60*60*24)); // Gets the number in days 
            if(diffInDays == 12)
            {
                int memberID = ContentArray[i].getCheckoutMemberId();
                String memberEmail= Member.getMember(memberID).getEmail();
                System.out.printf("12 day reminder sent to ",memberEmail); //Subject to change, give me any feedback
            }
            if(diffInDays == 14)
            {
                int memberID = ContentArray[i].getCheckoutMemberId();
                String memberEmail= Member.getMember(memberID).getEmail();
                System.out.printf("14 day reminder sent to ",memberEmail); //Subject to change, give me any feedback
            }
        }    
    }
}
