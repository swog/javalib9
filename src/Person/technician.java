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

	public technician() {
	}

	public technician(int EmpID, String Dept, String Title) {

		super(EmpID, Dept, Title);
	}

	public String toString() {
		return "";
	}

	public static void returnItem(Book book){
		String File = "LibraryContentFiles/BookList.csv";
		String newStatus = "Not Checked Out";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,"books");
		int i = bookCollection.searchItemByIdentifierForIndex(book.getIdentifier());
		Content[] bookArray = bookCollection.getContentArray();
		if(bookArray[i].getCheckoutStatus() == "Checked Out"){
			bookArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeBookCollectionIntoFile(bookCollection,File);
			return;
		}
		return;
	}

	public static void returnItem(DVD dvd){
		String File = "LibraryContentFiles/DVDList.csv";
		String newStatus = "Not Checked Out";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,"DVDs");
		int i = dvdCollection.searchItemByIdentifierForIndex(dvd.getIdentifier());
		Content[] dvdArray = dvdCollection.getContentArray();
		if(dvdArray[i].getCheckoutStatus() == "Checked Out"){
			dvdArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeBookCollectionIntoFile(dvdCollection,File);
			return;
		}
		return;
	}

	public static void returnItem(Newspaper newspaper){
		String File = "LibraryContentFiles/NewsPaperList.csv";
		String newStatus = "Not Checked Out";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File,"newspaper");
		int i = newspaperCollection.searchItemByIdentifierForIndex(newspaper.getIdentifier());
		Content[] newspaperArray = newspaperCollection.getContentArray();
		if(newspaperArray[i].getCheckoutStatus() == "Checked Out"){
			newspaperArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeBookCollectionIntoFile(newspaperCollection,File);
			return;
		}
		return;
	}

	public static void returnItem(Journal journal){
		String File = "LibraryContentFiles/JournalList.csv";
		String newStatus = "Not Checked Out";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,"journals");
		int i = journalCollection.searchItemByIdentifierForIndex(journal.getIdentifier());
		Content[] journalArray = journalCollection.getContentArray();
		if(journalArray[i].getCheckoutStatus() == "Checked Out"){
			journalArray[i].setCheckoutStatus(newStatus);
			LibraryFileReader.writeBookCollectionIntoFile(journalCollection,File);
			return;
		}
		return;
	}
}
