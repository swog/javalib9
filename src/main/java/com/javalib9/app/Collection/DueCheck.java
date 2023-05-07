package com.javalib9.app.Collection;

import java.util.ArrayList;
import java.util.Date;

import com.javalib9.app.Collection.*;
import com.javalib9.app.Content.*;
import com.javalib9.app.Identifier.ISBN;
import com.javalib9.app.Person.*;
import com.javalib9.app.LibraryFileReader.*;


public class DueCheck {


    
    public DueCheck()
    {

    }
    public  void newCheckOverdues(Collection library)
    {
        //Collection library = new Collection("CheckoutCollection");
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
                System.out.printf("12 day reminder sent to ",memberEmail); //Subject to change, give me any feedback
            }
            if(diffInDays == 14)
            {
                String memberEmail= getEmailByContent(ContentArray[i]);
                System.out.printf("14 day reminder sent to ",memberEmail); //Subject to change, give me any feedback
            }
            if(diffInDays >= 15 && diffInDays < 31)
            {
                AddFine(ContentArray[i]);
                String memberEmail= getEmailByContent(ContentArray[i]);
                System.out.printf("Item is passed due! $1 has been fined to: ",memberEmail);
            }
            if(diffInDays == 31)
            {
                ContentArray[i].markLost();
                String memberEmail= getEmailByContent(ContentArray[i]);
                String memberAddress = getAddressByContent(ContentArray[i]);
                System.out.printf("Item has been lost. Full price of content charged to ",memberEmail);
                System.out.printf("Letter sent to:  ",memberAddress);
            }
        }    
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
    /*public static void main(String[] args)
    {
        Date d1= new Date();
        Date d2 = new Date(123,03,30);
        long diffInMillies = Math.abs(d2.getTime() - d1.getTime()); //Gets the number of milliseconds since the content has been checked out
        int diffInDays =  (int) (diffInMillies / (1000*60*60*24));
        System.out.println(diffInDays);
        ISBN ISBN = new ISBN("0123456789");
        Book testBook1 = new Book("Book1",ISBN,"Checked Out",-1,d1);
        Book testBook2 = new Book("Book2",ISBN,"Checked Out",321,d2);
        Book testBook3 = new Book("Book3",ISBN,"Not Checked Out",-1,d2);
        Content [] contentArray = new Content[3];
        contentArray[0]=testBook1;
        contentArray[1]=testBook2;
        contentArray[2]=testBook3;
        Student student1 = new Student("Johnny", "Mizzou", d2, "JohhnyEmail", null, 0,-1,321);
        Professor prof1= new Professor("Toby", "Columbia", d2, "TobyEmail", null, 0, 321);
        
        people[0]=student1;
        people[1]=prof1;
        Person.setPeople(people);
        Collection library = new Collection("TestLibrary", contentArray);
        DueCheck dueCheck = new DueCheck();
        dueCheck.newCheckOverdues(library);
    }*/
}
