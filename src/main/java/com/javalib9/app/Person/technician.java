package com.javalib9.app.Person;
import com.javalib9.app.Content.Content;
import com.javalib9.app.Content.Book;
import com.javalib9.app.Content.DVD;
import com.javalib9.app.Content.Newspaper;
import com.javalib9.app.Content.Journal;
import com.javalib9.app.Collection.Collection;
import com.javalib9.app.LibraryFileReader.LibraryFileReader;
import com.javalib9.app.Identifier.*;
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
		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutStatus() == "Lost" || journalArray[i].getCheckoutStatus() == "Checked Out"){
			journalArray[i].markNotCheckedOut();
			LibraryFileReader.writeJournalCollectionIntoFile(journalCollection,File);
			return true;
		}
		return false;
	}

	/*public static void returnAll(Collection returnItems){
		Content[] items = returnItems.getContentArray();
		for(int i = 0;i< items.length;i++){
			if(items[i] instanceof Book){
				returnBook(items[i]);
			}else if(items[i] instanceof DVD){
				returnDVD(items[i]);
			}else if(items[i] instanceof Newspaper){
				returnNewspaper(items[i]);
			}else if(items[i] instanceof Journal){
				returnJournal(items[i]);
			}
		}
		return;
	}*/

	public static boolean returnItem(String itemType,String identifier){  //returns true if the item is able to be returned and false if not
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType == "Book" || itemType == "DVD"){
			if(identifier.length() != 10){
				return false;
			}
			isbn = new ISBN(identifier);
		}else if(itemType == "Journal" || itemType == "NewsPaper"){
			if(identifier.length() != 8){
				return false;
			}
			issn = new ISSN(identifier);
		}else{
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
	
	public static boolean borrowItem(String itemType,String identifier,int memberID){ //returns true if item is able to be borrowed and false if not
		ISBN isbn = null;
		ISSN issn = null;
		if(itemType == "Book" || itemType == "DVD"){
			if(identifier.length() != 10){
				return false;
			}
			isbn = new ISBN(identifier);
		}else if(itemType == "Journal" || itemType == "NewsPaper"){
			if(identifier.length() != 8){
				return false;
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
			case "NewsPaper":
				if(borrowNewspaper(issn, memberID)){
					return true;
				};
				break;
		}

		return false;
	}

	public static boolean borrowBook(ISBN isbn,int memberID){
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");
		int i = bookCollection.searchItemByIdentifierForIndex(isbn);
		Content[] bookArray = bookCollection.getContentArray();
		if(bookArray[i].getCheckoutStatus() == "Not Checked Out"){
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
		Content[] dvdArray = dvdCollection.getContentArray();
		if(dvdArray[i].getCheckoutStatus() == "Not Checked Out"){
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
		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutStatus() == "Not Checked Out"){
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
		Content[] newspaperArray = newspaperCollection.getContentArray();
		if(newspaperArray[i].getCheckoutStatus() == "Not Checked Out"){
			Date now = new Date();
			newspaperArray[i].markCheckedOut(memberID,now);
			LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection,File);
			return true;
		}
		return false;
	}

}
