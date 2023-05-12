package com.javalib9.app.Person;

import com.javalib9.app.Content.Content;
import com.javalib9.app.Content.Book;
import com.javalib9.app.Content.DVD;
import com.javalib9.app.Content.Newspaper;
import com.javalib9.app.Content.Journal;
import com.javalib9.app.Collection.Collection;
import com.javalib9.app.LibraryFileReader.LibraryFileReader;
import com.javalib9.app.LibraryFileReader.PersonFileReader;
import com.javalib9.app.Identifier.*;

import java.util.ArrayList;
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
	public static void addBook(String title, ISBN identifier) {
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File, "books");
		Content book = new Book(title, identifier);
		bookCollection.addItem(book);
		LibraryFileReader.writeBookCollectionIntoFile(bookCollection, File);
		return;
	}

	// function to add a DVD to the library

	public static void addDVD(String title, ISBN identifier) {
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File, "DVDs");
		Content dvd = new DVD(title, identifier);
		dvdCollection.addItem(dvd);
		LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection, File);
		return;
	}

	// function to add a newspaper to the library
	public static void addNewspaper(String title, ISSN identifier) {
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File, "newspaper");
		Content newspaper = new Newspaper(title, identifier);
		newspaperCollection.addItem(newspaper);
		LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection, File);
		return;
	}

	// function to add a journal to the library
	public static void addJournal(String title, ISSN identifier) {
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File, "journal");
		Content journal = new Journal(title, identifier);
		journalCollection.addItem(journal);
		LibraryFileReader.writeJournalCollectionIntoFile(journalCollection, File);
		return;
	}

	// function to add any item using switch case
	public static void addItem(String identifier, String ContentType, String title) throws InvalidIdentifierException{

		ISBN isbn = null;
		ISSN issn = null;

		if (ContentType == "Book" || ContentType == "DVD") {
			try {
				isbn = new ISBN(identifier);
			} catch (InvalidIdentifierException e) {
				throw (e);
			}
		} else if (ContentType == "Newspaper" || ContentType == "Journal") {
			try {
				issn = new ISSN(identifier);
			} catch (InvalidIdentifierException e) {
				throw (e);
			}
		} else {
			System.out.println("Invalid type");
			return;
		}
		switch (ContentType) {
			case "Book":
				addBook(title, isbn);
				break;
			case "DVD":
				addDVD(title, isbn);
				break;
			case "Newspaper":
				addNewspaper(title, issn);
				break;
			case "Journal":
				addJournal(title, issn);
				break;
			default:
				System.out.println("Invalid type");
				break;
		}
		return;
	}

	// function to remove a book from the library by identifier
	public static boolean removeBookByIdentifier(ISBN identifier) {
		String File = "LibraryContentFiles/BookList.csv";
		Collection bookCollection = LibraryFileReader.readFileIntoCollection(File, "books");
		if ( bookCollection.removeItemByIdentifier(identifier) == null ) return false;
		LibraryFileReader.writeBookCollectionIntoFile(bookCollection, File);
		return true;
	}

	// function to remove a DVD from the library by identifier
	public static boolean removeDVDByIdentifier(ISBN identifier) {
		String File = "LibraryContentFiles/DVDList.csv";
		Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File, "DVDs");
		if ( dvdCollection.removeItemByIdentifier(identifier) == null ) return false;
		LibraryFileReader.writeDVDCollectionIntoFile(dvdCollection, File);
		return true;
	}

	// function to remove a newspaper from the library by identifier
	public static boolean removeNewspaperByIdentifier(ISSN identifier) {
		String File = "LibraryContentFiles/NewspaperList.csv";
		Collection newspaperCollection = LibraryFileReader.readFileIntoCollection(File, "newspaper");
		if ( newspaperCollection.removeItemByIdentifier(identifier) == null) return false;
		LibraryFileReader.writeNewspaperCollectionIntoFile(newspaperCollection, File);
		return true;
	}

	// function to remove a journal from the library by identifier
	public static boolean removeJournalByIdentifier(ISSN identifier) {
		String File = "LibraryContentFiles/JournalList.csv";
		Collection journalCollection = LibraryFileReader.readFileIntoCollection(File, "journal");
		if ( journalCollection.removeItemByIdentifier(identifier) == null ) return false;
		LibraryFileReader.writeJournalCollectionIntoFile(journalCollection, File);
		return true;
	}

	// function to remove item by identifier
	public static boolean removeItem(String identifier) {
		ISBN isbn = null;
		ISSN issn = null;
		
		if ( identifier.length() == 10){
			try {
				isbn = new ISBN(identifier);

				if ( removeBookByIdentifier(isbn) ) return true;
				if ( removeDVDByIdentifier(isbn) ) return true;
				return false;

			} catch (InvalidIdentifierException e) {
				throw (e);
			}
		}

		else if ( identifier.length() == 8){

			try {
				issn = new ISSN(identifier);
				if ( removeNewspaperByIdentifier(issn) ) return true;
				if ( removeJournalByIdentifier(issn) ) return true;
				return false;
			} catch (InvalidIdentifierException e) {
				throw(e);
			}
		} else {
			throw ( new InvalidIdentifierException("No identifier of that length is valid"));
		}

	}

	// Help customers with resources

	// function to find a book in the library by identifier
	// public static Content findBookByIdentifier(Identifier identifier) {
	// String File = "LibraryContentFiles/BookList.csv";
	// Collection bookCollection = LibraryFileReader.readFileIntoCollection(File,
	// "books");
	// return bookCollection.getItemByIdentifier(identifier);
	// }

	// // function to find a DVD in the library by identifier
	// public static Content findDVDByIdentifier(Identifier identifier) {
	// String File = "LibraryContentFiles/DVDList.csv";
	// Collection dvdCollection = LibraryFileReader.readFileIntoCollection(File,
	// "DVDs");
	// return dvdCollection.getItemByIdentifier(identifier);
	// }

	// // function to find a newspaper in the library by identifier
	// public static Content findNewspaperByIdentifier(Identifier identifier) {
	// String File = "LibraryContentFiles/NewspaperList.csv";
	// Collection newspaperCollection =
	// LibraryFileReader.readFileIntoCollection(File, "newspaper");
	// return newspaperCollection.getItemByIdentifier(identifier);
	// }

	// // function to find a journal in the library by identifier
	// public static Content findJournalByIdentifier(Identifier identifier) {
	// String File = "LibraryContentFiles/JournalList.csv";
	// Collection journalCollection = LibraryFileReader.readFileIntoCollection(File,
	// "journal");
	// return journalCollection.getItemByIdentifier(identifier);
	// }

	// Manage Memberships

	// function to add student to people.csv
	public static void addStudent(String name, String address, Date dob, String email, SSN social, int id,
			int professorId) {
				
		String File = "LibraryContentFiles/People.csv";
		ArrayList<Person> people = PersonFileReader.readStudentFile(File);
		Student student = new Student(name, address, dob, email, social, id, 0, professorId);
		System.out.println("About to print the created student");
		System.out.println(student.toString());
		people.add(student);
		PersonFileReader.writeStudentFile(people, File);
	}

	// function to add professor to people.csv
	public static void addProfessor(String name, String address, Date dob, String email, SSN social, int id) {
		String File = "LibraryContentFiles/People.csv";
		ArrayList<Person> people = PersonFileReader.readStudentFile(File);
		Professor professor = new Professor(name, address, dob, email, social, id, 0);
		people.add(professor);
		PersonFileReader.writeProfessorFile(people, File);
	}

	// function to remove person by id
	public static void removeMember(int id) {
		String File = "LibraryContentFiles/People.csv";
		ArrayList<Person> people = PersonFileReader.readStudentFile(File);
		PersonFileReader.removePersonById(people, id);
		PersonFileReader.writeStudentFile(people, File);
	}

}
