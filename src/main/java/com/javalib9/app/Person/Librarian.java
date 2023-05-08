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


public class Librarian extends Employee {

	public Librarian() {
	}

	public Librarian(
			int EmpID,
			String Dept,
			String Title,
			String Name,
			String Address,
			Date Dob,
			String Email,
			SSN Social) {
		super(EmpID, Dept, Title, Name, Address, Dob, Email, Social);
	}

	public String toString() {
		return "";
	}

	// Manage Collections

	// function to add a book to the library
	public static void addBook(Content book) {
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File, "books");
		bookCollection.addItem(book);
		LibraryFileReader.writeBookCollectionIntoFile(bookCollection, File);
		return;
	}

	// function to add a DVD to the library
	public static void addDVD(Content dvd) {
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File, "DVDs");
		dvdCollection.addItem(dvd);
		LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection, File);
		return;
	}

	// function to add a newspaper to the library
	public static void addNewspaper(Content newspaper) {
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File, "newspaper");
		newspaperCollection.addItem(newspaper);
		LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection, File);
		return;
	}

	// function to add a journal to the library
	public static void addJournal(Content journal) {
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File, "journal");
		journalCollection.addItem(journal);
		LibraryFileReader.writeJournalCollectionIntoFile(journalCollection, File);
		return;
	}

	// function to remove a book from the library by identifier
	public static void removeBookByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File, "books");
		bookCollection.removeItemByIdentifier(identifier);
		LibraryFileReader.writeBookCollectionIntoFile(bookCollection, File);
		return;
	}

	// function to remove a DVD from the library by identifier
	public static void removeDVDByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File, "DVDs");
		dvdCollection.removeItemByIdentifier(identifier);
		LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection, File);
		return;
	}

	// function to remove a newspaper from the library by identifier
	public static void removeNewspaperByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File, "newspaper");
		newspaperCollection.removeItemByIdentifier(identifier);
		LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection, File);
		return;
	}

	// function to remove a journal from the library by identifier
	public static void removeJournalByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File, "journal");
		journalCollection.removeItemByIdentifier(identifier);
		LibraryFileReader.writeJournalCollectionIntoFile(journalCollection, File);
		return;
	}

	// Help customers with resources

	// function to find a book in the library by identifier
	public static Content findBookByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File, "books");
		return bookCollection.getItemByIdentifier(identifier);
	}

	// function to find a DVD in the library by identifier
	public static Content findDVDByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File, "DVDs");
		return dvdCollection.getItemByIdentifier(identifier);
	}

	// function to find a newspaper in the library by identifier
	public static Content findNewspaperByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File, "newspaper");
		return newspaperCollection.getItemByIdentifier(identifier);
	}

	// function to find a journal in the library by identifier
	public static Content findJournalByIdentifier(Identifier identifier) {
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File, "journal");
		return journalCollection.getItemByIdentifier(identifier);
	}

	// Manage Memberships

	// function to get member id

}
