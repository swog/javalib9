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
    public ArrayList<String[]> newCheckOverdues()
    {
        Collection library = LibraryFileReader.readAllContentFilesIntoCollection("CheckoutCollection");
        Collection CheckedOutCollection = library.getCheckedOutCollectionSubset("CheckedOutCollection");
        Content [] ContentArray = CheckedOutCollection.getContentArray();

        ArrayList<String[]> returningArrayList = new ArrayList<>();


        for ( int i = 0 ; i < ContentArray.length ; i++ ){
            Date d1= new Date();
            Date d2 = ContentArray[i].getCheckoutDate();
            long diffInMillies = Math.abs(d2.getTime() - d1.getTime()); //Gets the number of milliseconds since the content has been checked out
            int diffInDays =  (int) (diffInMillies / (1000*60*60*24)); // Gets the number in days 


            String memberEmail= getEmailByContent(ContentArray[i]);
            String output[] = new String[4];

            output[0] = memberEmail;
            output[1] = Integer.toString(diffInDays);
            if(diffInDays == 12)
            {
                output[2] = "Approaching Deadline";
                output[3] = "No fine added";
            }
            if(diffInDays == 14)
            {
                output[2] = "Deadline Reached";
                output[3] = "No fine added";
            }
            if(diffInDays >= 15 && diffInDays < 31)
            {
                AddFine(ContentArray[i]);
                output[2] = "Item Is Passed Due!";
                output[3] = "$1 fine has been added";
            }
            if(diffInDays == 31)
            {
                ContentArray[i].markLost();
                output[2] = "Item Has Been Considered Lost";
                output[3] = "Full price of content has been charged";
            }
            returningArrayList.add(output);
        }   
        return returningArrayList;
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
