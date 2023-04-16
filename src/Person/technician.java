package src.Person;
import src.Content.Content;
import src.Content.Book;
import src.Content.DVD;
import src.Content.Newspaper;
import src.Content.Journal;
import src.Collection.Collection;
import src.LibraryFileReader.LibraryFileReader;
import src.Identifier.*;

public class technician extends Employee {

	public technician(){
	}

	public technician(int EmpID, String Dept, String Title) {
		super(EmpID, Dept, Title);
	}

	public String toString() {
		return "";
	}

	public static void returnBook(Content book){
		String File = "LibraryContentFiles/BookList.csv";
		String newStatus = "Not Checked Out";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");
		int i = bookCollection.searchItemByIdentifierForIndex(book.getIdentifier());
		Content[] bookArray = bookCollection.getContentArray();
		if(bookArray[i].getCheckoutStatus() == "Checked Out" || bookArray[i].getCheckoutStatus() == "Lost"){
			bookArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeBookCollectionIntoFile(bookCollection,File);
			return;
		}
		return;
	}

	public static void returnDVD(Content dvd){
		String File = "LibraryContentFiles/DVDList.csv";
		String newStatus = "Not Checked Out";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");
		int i = dvdCollection.searchItemByIdentifierForIndex(dvd.getIdentifier());
		Content[] dvdArray = dvdCollection.getContentArray();
		if(dvdArray[i].getCheckoutStatus() == "Checked Out" || dvdArray[i].getCheckoutStatus() == "Lost"){
			dvdArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection,File);
			return;
		}
		return;
	}

	public static void returnNewspaper(Content newspaper){
		String File = "LibraryContentFiles/NewsPaperList.csv";
		String newStatus = "Not Checked Out";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");
		int i = newspaperCollection.searchItemByIdentifierForIndex(newspaper.getIdentifier());
		Content[] newspaperArray = newspaperCollection.getContentArray();
		if(newspaperArray[i].getCheckoutStatus() == "Checked Out" || newspaperArray[i].getCheckoutStatus() == "Lost"){
			newspaperArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection,File);
			return;
		}
		return;
	}

	public static void returnJournal(Content journal){
		String File = "LibraryContentFiles/JournalList.csv";
		String newStatus = "Not Checked Out";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");
		int i = journalCollection.searchItemByIdentifierForIndex(journal.getIdentifier());
		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutStatus() == "Checked Out" || journalArray[i].getCheckoutStatus() == "Lost"){
			journalArray[i].setCheckoutStatus(newStatus);
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
