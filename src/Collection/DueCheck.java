package src.Collection;

import java.util.Date;

import src.Collection.Collection;
import src.Content.*;
import src.Person.*;
import src.LibraryFileReader.LibraryFileReader;


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
            if(diffInDays <= 15 && diffInDays > 31)
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
        System.out.println("Enter Test");
        String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = new Collection("TestCollection");
        bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");
        DueCheck newCheck = new DueCheck();
        newCheck.newCheckOverdues(bookCollection);
    }*/
}
