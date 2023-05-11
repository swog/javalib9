package com.javalib9.app.Collection;

import java.util.ArrayList;
import java.util.Date;
import com.javalib9.app.Content.*;
import com.javalib9.app.Person.*;
import com.javalib9.app.LibraryFileReader.*;


public class DueCheck {


    
    public DueCheck()
    {

    }
    public ArrayList<String> newCheckOverdues()
    {
        Collection library = LibraryFileReader.readAllContentFilesIntoCollection("CheckoutCollection");
        Collection CheckedOutCollection = library.getCheckedOutCollectionSubset("CheckedOutCollection");
        Content [] ContentArray = CheckedOutCollection.getContentArray();
        for ( int i = 0 ; i < ContentArray.length ; i++ ){
            Date d1= new Date();
            Date d2 = ContentArray[i].getCheckoutDate();
            long diffInMillies = Math.abs(d2.getTime() - d1.getTime()); //Gets the number of milliseconds since the content has been checked out
            int diffInDays =  (int) (diffInMillies / (1000*60*60*24)); // Gets the number in days 
            if(diffInDays == 12)
            {
                String memberEmail= getEmailByContent(ContentArray[i]);
                ArrayList<String> output = new ArrayList<String>();
                output.add(memberEmail);
                output.add(Integer.toString(diffInDays));
                output.add("Approaching Deadline");
                output.add("No fine added");
                return output;
            }
            if(diffInDays == 14)
            {
                String memberEmail= getEmailByContent(ContentArray[i]);
                ArrayList<String> output = new ArrayList<String>();
                output.add(memberEmail);
                output.add(Integer.toString(diffInDays));
                output.add("Deadline Reached");
                output.add("No fine added");
                return output;
            }
            if(diffInDays >= 15 && diffInDays < 31)
            {
                AddFine(ContentArray[i]);
                String memberEmail= getEmailByContent(ContentArray[i]);
                ArrayList<String> output = new ArrayList<String>();
                output.add(memberEmail);
                output.add(Integer.toString(diffInDays));
                output.add("Item Is Passed Due!");
                output.add("$1 fine has been added");
                return output;
            }
            if(diffInDays == 31)
            {
                ContentArray[i].markLost();
                String memberEmail= getEmailByContent(ContentArray[i]);
                ArrayList<String> output = new ArrayList<String>();
                output.add(memberEmail);
                output.add(Integer.toString(diffInDays));
                output.add("Item Has Been Considered Lost");
                output.add("Full price of content has been charged");
                return output;
            }
        }   
        return null;
    }
    public int getIDByContent (Content content)
    {
        int memberID = content.getCheckoutMemberId();
        return memberID;
    }
    public String getEmailByContent(Content content)
    {
        int memberID = getIDByContent(content);
        String memberEmail= Member.getMember(memberID).getEmail();
        return memberEmail;
    }
    public  String getAddressByContent(Content content)
    {
        int memberID = getIDByContent(content);
        String memberAddress = Member.getMember(memberID).getAddress();
        return memberAddress;
    }
    public void AddFine(Content content)
    {
        int memberID = getIDByContent(content);
        Member member = Member.getMember(memberID);
        member.IncBalance();
    }
}
