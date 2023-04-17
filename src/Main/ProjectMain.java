/*
 * LIST OF EVERYTHING THAT WORKS AND DATE OF WORK CONFIRMATION
 *
 * LIST OF EVERYTHING THAT NEEDS TO BE TESTED
 *
 * LIBRARYFILEREADER PACKAGE
 * > LIBRARYFILEREADER CLASS
 * >> readFileIntoCollection
 * >> writeCatchAllCollectionIntoFile
 * >> writeBookCollectionIntoFile
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
import src.Content.Content;


public class ProjectMain{

    public static void main (String[] args){
        Collection testingCollection = LibraryFileReader.readFileIntoCollection("LibraryContentFiles/BookList.csv", "test collection");

        Content myContent = testingCollection.getItemByIndex(testingCollection.length()-1);
    }

}
