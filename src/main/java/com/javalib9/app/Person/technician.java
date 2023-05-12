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
		if(bookArray[i].getCheckoutStatus().equals("Lost") || bookArray[i].getCheckoutStatus().equals("Checked Out")){
			bookArray[i].markNotCheckedOut();
			System.out.println("the following is about to be written:");
			bookCollection.printCollectionContentsToConsole();
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
		if(dvdArray[i].getCheckoutStatus().equals("Lost") || dvdArray[i].getCheckoutStatus().equals("Checked Out")){
			dvdArray[i].markNotCheckedOut();
			LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection,File);
			return true;
		}
		return false;
	}

	public static boolean returnNewspaper(ISSN issn){
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection NewspaperCollection = LibraryFileReader.readFileIntoCollection(File,"Newspaper");

		int i = NewspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] NewspaperArray = NewspaperCollection.getContentArray();
		if(NewspaperArray[i].getCheckoutStatus().equals("Lost") || NewspaperArray[i].getCheckoutStatus().equals("Checked Out")){
			NewspaperArray[i].markNotCheckedOut();
			LibraryFileReader.writeNewspaperCollectionIntoFile(NewspaperCollection,File);
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
		if(journalArray[i].getCheckoutStatus().equals("Lost") || journalArray[i].getCheckoutStatus().equals("Checked Out")){
			journalArray[i].markNotCheckedOut();
			LibraryFileReader.writeJournalCollectionIntoFile(journalCollection,File);
			return true;
		}
		return false;
	}

	public static boolean returnItem(String itemType,String identifier){  //returns true if the item is able to be returned and false if not
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType.equals("Book") || itemType.equals("DVD")){ 
			if(identifier.length() != 10){ //checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISBN");
			}
			isbn = new ISBN(identifier);
		}else if(itemType.equals("Journal") || itemType.equals("Newspaper")){
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
			case "Newspaper":
				if(returnNewspaper(issn)){
					return true;
				}
				break;
		}

		return false;
	}
	
	public static boolean borrowItem(String itemType,String identifier,int memberID) throws InvalidIdentifierException{ //returns true if item is able to be borrowed and false if not
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType.equals("Book") || itemType.equals("DVD")){
			if(identifier.length() != 10){	//checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISBN");
			}
			isbn = new ISBN(identifier);
		}else if(itemType.equals("Journal") || itemType.equals("Newspaper")){
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
		Collection NewspaperCollection = LibraryFileReader.readFileIntoCollection(File,"Newspaper");

		int i = NewspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content[] NewspaperArray = NewspaperCollection.getContentArray();
		if(NewspaperArray[i].getCheckoutStatus().equals("Not Checked Out")){
			Date now = new Date();
			NewspaperArray[i].markCheckedOut(memberID,now);
			LibraryFileReader.writeNewspaperCollectionIntoFile(NewspaperCollection,File);
			return true;
		}
		return false;
	}

	public static String findLocation(String itemType,String identifier){
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType.equals("Book") || itemType.equals("DVD")){
			if(identifier.length() != 10){	//checks if the identifier entered is valid for the item type
				throw new InvalidIdentifierException("Invalid ISBN");
			}
			isbn = new ISBN(identifier);
		}else if(itemType.equals("Journal") || itemType.equals("Newspaper")){
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
		Collection NewspaperCollection = LibraryFileReader.readFileIntoCollection(File,"Newspaper");
		NewspaperCollection.sortCollectionByIdentifier();

		int i = NewspaperCollection.searchItemByIdentifierForIndex(issn);
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
		Collection NewspaperCollection = LibraryFileReader.readFileIntoCollection(File,"Newspaper");

		int i = NewspaperCollection.searchItemByIdentifierForIndex(issn);
		if ( i == -1) throw new InvalidIdentifierException("Item with that identifier not found. Check your value");

		Content array[] = NewspaperCollection.getContentArray();
		String title = array[i].getTitle();

		return title;
	}

}

