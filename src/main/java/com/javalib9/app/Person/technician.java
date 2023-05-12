package com.javalib9.app.Person;
import com.javalib9.app.Content.Content;
import com.javalib9.app.Content.Book;
import com.javalib9.app.Content.DVD;
import com.javalib9.app.Content.Newspaper;
import com.javalib9.app.Content.Journal;
import com.javalib9.app.Collection.Collection;
import com.javalib9.app.LibraryFileReader.LibraryFileReader;
import com.javalib9.app.Identifier.*;
import com.javalib9.app.Identifier.InvalidIdentifierException;
import java.util.Date;
import com.javalib9.app.Identifier.ISBN;
import com.javalib9.app.Identifier.ISSN;

public class technician extends Employee {
	
	public technician(){
	}

	public technician(
		int EmpID,
		String Dept,
		String Title,
		String Name,
		String Address,
		Date Dob,
		String Email,
		SSN Social
	) {
		super(EmpID, Dept, Title, Name, Address, Dob, Email, Social);
	}

	public static boolean returnBook(ISBN isbn){
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");

		int i = bookCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] bookArray = bookCollection.getContentArray();
		if(bookArray[i].getCheckoutStatus() == "Lost" || bookArray[i].getCheckoutStatus() == "Checked Out"){
			bookArray[i].markNotCheckedOut();
			LibraryFileReader.writeBookCollectionIntoFile(bookCollection,File);
			return true;
		}
		return false;
	}

	public static boolean returnDVD(ISBN isbn){
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");

		int i = dvdCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] dvdArray = dvdCollection.getContentArray();
		if(dvdArray[i].getCheckoutStatus() == "Lost" || dvdArray[i].getCheckoutStatus() == "Checked Out"){
			dvdArray[i].markNotCheckedOut();
			LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection,File);
			return true;
		}
		return false;
	}

	public static boolean returnNewspaper(ISSN issn){
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");

		int i = newspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] newspaperArray = newspaperCollection.getContentArray();
		if(newspaperArray[i].getCheckoutStatus() == "Lost" || newspaperArray[i].getCheckoutStatus() == "Checked Out"){
			newspaperArray[i].markNotCheckedOut();
			LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection,File);
			return true;
		}
		return false;
	}

	public static boolean returnJournal(ISSN issn){
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");

		int i = journalCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutStatus() == "Lost" || journalArray[i].getCheckoutStatus() == "Checked Out"){
			journalArray[i].markNotCheckedOut();
			LibraryFileReader.writeJournalCollectionIntoFile(journalCollection,File);
			return true;
		}
		return false;
	}

	public static boolean returnItem(String itemType,String identifier){  //returns true if the item is able to be returned and false if not
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType == "Book" || itemType == "DVD"){ 
			if(identifier.length() != 10){ //checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISBN");
			}
			isbn = new ISBN(identifier);
		}else if(itemType == "Journal" || itemType == "NewsPaper"){
			if(identifier.length() != 8){ //checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISSN");
			}
			issn = new ISSN(identifier);
		}else{ //if a valid itemType is not entered returns false
			return false;
		}

		switch(itemType){
			case "Book":
				if(returnBook(isbn)){
					return true;
				}
				break;
			case "DVD":
				if(returnDVD(isbn)){
					return true;
				}
				break;
			case "Journal":
				if(returnJournal(issn)){
					return true;
				}
				break;
			case "NewsPaper":
				if(returnNewspaper(issn)){
					return true;
				}
				break;
		}

		return true;
	}
	
	public static boolean borrowItem(String itemType,String identifier,int memberID) throws InvalidIdentifierException{ //returns true if item is able to be borrowed and false if not
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType == "Book" || itemType == "DVD"){
			if(identifier.length() != 10){	//checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISBN");
			}
			isbn = new ISBN(identifier);
		}else if(itemType == "Journal" || itemType == "Newspaper"){
			if(identifier.length() != 8){	//checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISSN");
			}
			issn = new ISSN(identifier);
		}else{
			return false;
		}

		switch(itemType){
			case "Book":
				if(borrowBook(isbn, memberID)){
					return true;
				}
				break;
			case "DVD":
				if(borrowDVD(isbn, memberID)){
					return true;
				}
				break;
			case "Journal":
				if(borrowJournal(issn, memberID)){
					return true;
				}
				break;
			case "Newspaper":
				if(borrowNewspaper(issn, memberID)){
					return true;
				};
				break;
		}

		return false;
	}

	public static boolean borrowBook(ISBN isbn,int memberID) throws InvalidIdentifierException{
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");

		/* HANDLE WHAT HAPPENS IF BOOK IS NOT FOUND FOR ALL BELOW (feel free to copy paste like so and add the "throws")  */
		int i = bookCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] bookArray = bookCollection.getContentArray();
		if(bookArray[i].getCheckoutStatus().equals("Not Checked Out")){
			Date now = new Date();
			bookArray[i].markCheckedOut(memberID,now);
			LibraryFileReader.writeBookCollectionIntoFile(bookCollection,File);
			return true;
		}
		return false;
	}

	public static boolean borrowDVD(ISBN isbn,int memeberID){
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");
		
		int i = dvdCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] dvdArray = dvdCollection.getContentArray();
		if(dvdArray[i].getCheckoutStatus().equals("Not Checked Out")){
			Date now = new Date();
			dvdArray[i].markCheckedOut(memeberID,now);
			LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection,File);
			return true;
		}
		return false;
	}

	public static boolean borrowJournal(ISSN issn,int memberID){
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");

		int i = journalCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutStatus().equals("Not Checked Out")){
			Date now = new Date();
			journalArray[i].markCheckedOut(memberID,now);
			LibraryFileReader.writeJournalCollectionIntoFile(journalCollection,File);
			return true;
		}
		return false;
	}

	public static boolean borrowNewspaper(ISSN issn,int memberID){
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");

		int i = newspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] newspaperArray = newspaperCollection.getContentArray();
		if(newspaperArray[i].getCheckoutStatus().equals("Not Checked Out")){
			Date now = new Date();
			newspaperArray[i].markCheckedOut(memberID,now);
			LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection,File);
			return true;
		}
		return false;
	}

	public static String findLocation(String itemType,String identifier){
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType == "Book" || itemType == "DVD"){
			if(identifier.length() != 10){	//checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISBN");
			}
			isbn = new ISBN(identifier);
		}else if(itemType == "Journal" || itemType == "Newspaper"){
			if(identifier.length() != 8){	//checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISSN");
			}
			issn = new ISSN(identifier);
		}

		int i = 0;
		String title = "";
		switch(itemType){
			case "Book":
				i = getBookIndex(isbn);
				title = getBookTitle(isbn);
				return (title + " is in spot " + i);
			case "DVD":
				i = getDVDindex(isbn);
				title = getDVDTitle(isbn);
				return (title + " is in spot " + i);
			case "Journal":
				i = getJournalIndex(issn);
				title = getJournalTitle(issn);
				return (title + " is in spot " + i);
			case "Newspaper":
				i = getNewspaperindex(issn);
				title = getNewspaperTitle(issn);
				return (title + " is in spot " + i);
		}

		return "";
	}	

	public static int getBookIndex(ISBN isbn){
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");
		bookCollection.sortCollectionByIdentifier();

		int i = bookCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		return i;
	}

	public static int getDVDindex(ISBN isbn){
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");
		dvdCollection.sortCollectionByIdentifier();

		int i = dvdCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		return i;
	}

	public static int getJournalIndex(ISSN issn){
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");
		journalCollection.sortCollectionByIdentifier();

		int i = journalCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		return i;

	}

	public static int getNewspaperindex(ISSN issn){
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");
		newspaperCollection.sortCollectionByIdentifier();

		int i = newspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		return i;
	}
	/***************************************************************/

	public static String getBookTitle(ISBN isbn){
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");

		int i = bookCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content array[] = bookCollection.getContentArray();
		String title = array[i].getTitle();

		return title;
	}

	public static String getDVDTitle(ISBN isbn){
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");

		int i = dvdCollection.searchItemByIdentifierForIndex(isbn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content array[] = dvdCollection.getContentArray();
		String title = array[i].getTitle();

		return title;

	}

	public static String getJournalTitle(ISSN issn){
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");

		int i = journalCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content array[] = journalCollection.getContentArray();
		String title = array[i].getTitle();

		return title;
	}

	public static String getNewspaperTitle(ISSN issn){
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");

		int i = newspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content array[] = newspaperCollection.getContentArray();
		String title = array[i].getTitle();

		return title;
	}

}

