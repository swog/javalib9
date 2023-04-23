/*
 * LIST OF EVERYTHING THAT WORKS AND DATE OF WORK CONFIRMATION
 * >> readFileIntoCollection 4/23
 * >>> readable file
 * >>> unreadable file
 * >> writeBookCollectionIntoFile
 * >>> readable file, replaced
 *
 * LIST OF EVERYTHING THAT NEEDS TO BE TESTED
 *
 * LIBRARYFILEREADER PACKAGE
 * > LIBRARYFILEREADER CLASS
 * >> writeCatchAllCollectionIntoFile
 * >>> into new file
 * >>> POTENTIAL FIX: enforce that must be written into a specific directory
 * >> writeDVDCollectionIntoFile
 * >> writeNewspaperCollectionIntoFile
 * >> writeJournalCollectionIntoFile
 *
 * COLLECTION PACKAGE:
 * > COLLECTION CLASS
 * >> addItem (Content item)
 * >> searchItemByIdentifierForIndex (Identifier identifier)
 * >> searchItemByIdentifierForContent (Identifier identifier)
 * >> getItemByIdentifier(Identifier identifier)
 * >> removeItemByIdentifier(Identifier identifier)
 * >> the subset methods
 * >> the sort methods
 * > DUECHECK CLASS
 * >> newCheckOverdues
 * >> getIDByContent
 * >> getEmailByContent
 * >> getAddressByContent
 * >> AddFine
 *  */

package src.Main;

import src.Collection.Collection;
import src.LibraryFileReader.LibraryFileReader;
import src.Identifier.Identifier;
import src.Identifier.ISBN;
import src.Identifier.ISSN;
import src.Content.Content;
import src.Content.Book;
import src.Content.Newspaper;
import src.Content.Journal;
import src.Content.DVD;


public class ProjectMain{

    public static void main (String[] args){

    }

}
