package src.Person;
import src.Content.Content;
import src.Content.Book;
import src.Content.DVD;
import src.Content.Newspaper;
import src.Content.Journal;
import src.Collection.Collection;
import src.LibraryFileReader.LibraryFileReader;
import src.Identifier.*;
import java.util.Date;

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

	public static void returnBook(Content book){
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");
		int i = bookCollection.searchItemByIdentifierForIndex(book.getIdentifier());
		Content[] bookArray = bookCollection.getContentArray();
		if(bookArray[i].getCheckoutDate() != null){
			bookArray[i].markNotCheckedOut();
			LibraryFileReader.writeBookCollectionIntoFile(bookCollection,File);
			return;
		}
		return;
	}

	public static void returnDVD(Content dvd){
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");
		int i = dvdCollection.searchItemByIdentifierForIndex(dvd.getIdentifier());
		Content[] dvdArray = dvdCollection.getContentArray();
		if(dvdArray[i].getCheckoutDate() != null){
			dvdArray[i].markNotCheckedOut();
			LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection,File);
			return;
		}
		return;
	}

	public static void returnNewspaper(Content newspaper){
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");
		int i = newspaperCollection.searchItemByIdentifierForIndex(newspaper.getIdentifier());
		Content[] newspaperArray = newspaperCollection.getContentArray();
		if(newspaperArray[i].getCheckoutDate() != null){
			newspaperArray[i].markNotCheckedOut();
			LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection,File);
			return;
		}
		return;
	}

	public static void returnJournal(Content journal){
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");
		int i = journalCollection.searchItemByIdentifierForIndex(journal.getIdentifier());
		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutDate() != null){
			journalArray[i].markNotCheckedOut();
			LibraryFileReader.writeJournalCollectionIntoFile(journalCollection,File);
			return;
		}
		return;
	}

	public static void returnAll(Collection returnItems){
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
	}
	
}
