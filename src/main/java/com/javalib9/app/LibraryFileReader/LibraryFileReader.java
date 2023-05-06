package com.javalib9.app.LibraryFileReader;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.javalib9.app.Collection.Collection;
import com.javalib9.app.Content.Content;
import com.javalib9.app.Content.Book;
import com.javalib9.app.Content.DVD;
import com.javalib9.app.Content.Newspaper;
import com.javalib9.app.Content.Journal;
import com.javalib9.app.Identifier.Identifier;
import com.javalib9.app.Identifier.ISBN;
import com.javalib9.app.Identifier.ISSN;
import com.javalib9.app.Identifier.InvalidIdentifierException;
/*
 * -------------------------------------------
 * A NOTE ON WHAT THE fileName PARAMETER SHOULD BE:
 * >> this should always be "LibraryContentFiles/[fileName].csv"
 * >> if someone wants I could change this behavior
 *
 * -------------------------------------------
 * STATIC METHODS
 * -------------------------------------------
 *
 * "I want to read a file into a collection object!"
 * > Collection readFileIntoCollection(String fileName)
 * >> searches for an exact file and puts everything from that file into a collection
 * >> throws "InvalidContentFileException" if the file attempted to be read is formatted incorrectly
 * >> returns null if file is not found, so check to make sure your Collection isn't null before using it
 *
 * -------------------------------------------
 * "I want to write my collection to a file!"
 * > If your collection has the same content type:
 * >> void writeBookCollectionIntoFile(Collection collectionToWriteToFile, String fileName)
 * >> void writeDVDCollectionIntoFile(Collection collectionToWriteToFile, String fileName)
 * >> void writeNewspaperCollectionIntoFile(Collection collectionToWriteToFile, String fileName)
 * >> void writeJournalCollectionIntoFile(Collection collectionToWriteToFile, String fileName)
 * > If your collection has variable content type: <<< THIS IS UNDER CONSTRUCTION AS OF 4/14 at 4:25 >>>
 * >> void writeCatchAllCollectionIntoFile(Collection collectionToWriteToFile, String fileName)
 * -------------------------------------------
 * */

public final class LibraryFileReader {

    private LibraryFileReader(){
        throw new UnsupportedOperationException();
    }

    public static Collection readFileIntoCollection(String fileName, String newCollectionName){

        ArrayList<String> fileLines = new ArrayList<>();

        try {
            Scanner myFileReader = new Scanner ( new File(fileName) );

            while ( myFileReader.hasNextLine() ){
                fileLines.add(myFileReader.nextLine());
            }


            myFileReader.close();

        } catch (FileNotFoundException e){

            e.printStackTrace();
            return null;

        }

        String[] tokenizedFirstLine = fileLines.get(0).split(",");

        ArrayList<Content> contentForNewCollection = null;

        try {
            if (tokenizedFirstLine[0].equals("Book")) {
                contentForNewCollection = parseBookFile(fileLines);
            } else if (tokenizedFirstLine[0].equals("DVD")) {
                contentForNewCollection = parseDVDFile(fileLines);
            } else if (tokenizedFirstLine[0].equals("Newspaper")) {
                contentForNewCollection = parseNewspaperFile(fileLines);
            } else if (tokenizedFirstLine[0].equals("Journal")) {
                contentForNewCollection = parseJournalFile(fileLines);
            } else if (tokenizedFirstLine[0].equals("Content Name")) {
                contentForNewCollection = parseCatchAllFile(fileLines);
            } else {
                throw new InvalidContentFileException("The file you chose is either not a content file or isn't formatted correctly");
            }
        } catch ( InvalidContentFileException e ){
            throw e;
        }

        return new Collection(newCollectionName, contentForNewCollection, "Identifier");
    }

    public static void writeCatchAllCollectionIntoFile(Collection collectionToWriteToFile, String fileLocation){
        writeCollectionIntoFile(collectionToWriteToFile, fileLocation, "Catch All", "Identifier");
    }
    public static void writeBookCollectionIntoFile(Collection collectionToWriteToFile, String fileLocation){
        Content[] contentArray = collectionToWriteToFile.getContentArray();
        for ( int i = 0 ; i < contentArray.length ; i++ ){
            if ( !( contentArray[i] instanceof Book ) ){
                throw new InvalidCollectionTypeFileWritingException("Not all items are books, call a different file writing function");
            }
        }
        writeCollectionIntoFile(collectionToWriteToFile, fileLocation, "Book", "ISBN");
    }
    public static void writeDVDCollectionIntoFile(Collection collectionToWriteToFile, String fileLocation){
        Content[] contentArray = collectionToWriteToFile.getContentArray();
        for ( int i = 0 ; i < contentArray.length ; i++ ){
            if ( !( contentArray[i] instanceof DVD) ){
                throw new InvalidCollectionTypeFileWritingException("Not all items are DVDs, call a different file writing function");
            }
        }
        writeCollectionIntoFile(collectionToWriteToFile, fileLocation, "DVD", "ISBN");
    }
    public static void writeJournalCollectionIntoFile(Collection collectionToWriteToFile, String fileLocation){
        Content[] contentArray = collectionToWriteToFile.getContentArray();
        for ( int i = 0 ; i < contentArray.length ; i++ ){
            if ( !( contentArray[i] instanceof Journal) ){
                throw new InvalidCollectionTypeFileWritingException("Not all items are journals, call a different file writing function");
            }
        }
        writeCollectionIntoFile(collectionToWriteToFile, fileLocation, "Journal", "ISSN");
    }
    public static void writeNewspaperCollectionIntoFile(Collection collectionToWriteToFile, String fileLocation){
        Content[] contentArray = collectionToWriteToFile.getContentArray();
        for ( int i = 0 ; i < contentArray.length ; i++ ){
            if ( !( contentArray[i] instanceof Newspaper) ){
                throw new InvalidCollectionTypeFileWritingException("Not all items are newspapers, call a different file writing function");
            }
        }
        writeCollectionIntoFile(collectionToWriteToFile, fileLocation, "Newspaper", "ISSN");
    }

    private static void writeCollectionIntoFile(Collection collectionToWriteToFile, String fileLocation, String listType, String identifierType){

        if ( listType.equals("Catch All") ){ // in the case the file needed to be written is a catch all file

            try {
                PrintWriter writer = new PrintWriter(fileLocation);

                String line1 = "Content Name,Content Type,Identifier,Status,Borrower Id,Date Borrowed";
                writer.println(line1);

                Content[] contentArray = collectionToWriteToFile.getContentArray();

                for (int i = 0; i < contentArray.length; i++) {

                    String identifierString = null;

                    if ( contentArray[i] instanceof Book ){
                        identifierString = "Book";
                    } else if ( contentArray[i] instanceof DVD ){
                        identifierString = "DVD";
                    } else if ( contentArray[i] instanceof Newspaper ){
                        identifierString = "Newspaper";
                    } else if ( contentArray[i] instanceof Journal ){
                        identifierString = "Journal";
                    } else {
                        writer.close();

                        throw new InvalidCollectionException("Item in Collection is invalid content. WARNING: unfinished file still written");
                    }

                    String newLine =
                        contentArray[i].getTitle().concat(",")
                        .concat(identifierString)
                        .concat(contentArray[i].getIdentifier().toString()).concat(",")
                        .concat(contentArray[i].getCheckoutStatus()).concat(",")
                        .concat(String.valueOf(contentArray[i].getCheckoutMemberId())).concat(",");

                    Date contentDateBorrowed = contentArray[i].getCheckoutDate();
                    String dateLine = "";
                    if ( contentDateBorrowed == null ){
                        dateLine = "NULL";
                    } else {
                        String tempDateString = contentDateBorrowed.toString();
                        String[] tokenizedTempDateString = tempDateString.split(" ");

                        String dayOfMonth = tokenizedTempDateString[2];
                        String month = tokenizedTempDateString[1];
                        String year = tokenizedTempDateString[5];

                        dateLine.concat(dayOfMonth).concat("-").concat(month).concat("-").concat(year);
                    }
                    newLine.concat(dateLine);

                    writer.println(newLine);
                }

                writer.close();



            } catch (Exception e){

            }

        } else { // for all other collections
            try {
                PrintWriter writer = new PrintWriter(fileLocation);

                String line1 = listType.concat(",").concat(identifierType).concat(",Status,Borrower Id,Date Borrowed");
                writer.println(line1);

                Content[] contentArray = collectionToWriteToFile.getContentArray();
                for (int i = 0; i < contentArray.length; i++) {

                    String newLine = contentArray[i].getTitle().concat(",")
                            .concat(contentArray[i].getIdentifier().toString()).concat(",")
                            .concat(contentArray[i].getCheckoutStatus()).concat(",")
                            .concat(String.valueOf(contentArray[i].getCheckoutMemberId())).concat(",");

                    Date contentDateBorrowed = contentArray[i].getCheckoutDate();
                    String dateLine = "";
                    if ( contentDateBorrowed == null ){
                        dateLine = "NULL";
                    } else {
                        String tempDateString = contentDateBorrowed.toString();
                        String[] tokenizedTempDateString = tempDateString.split(" ");

                        String dayOfMonth = tokenizedTempDateString[2];
                        String month = tokenizedTempDateString[1];
                        String year = tokenizedTempDateString[5];

                        dateLine.concat(dayOfMonth).concat("-").concat(month).concat("-").concat(year);
                    }
                    newLine.concat(dateLine);

                    writer.println(newLine);
                }

                writer.close();
            } catch (Exception e) { // change this
                e.printStackTrace();
            }
            
        }

    }

    private static ArrayList<Content> parseBookFile(ArrayList<String> fileLines){

        ArrayList<Content> filledArrayList = new ArrayList<>();
        for ( int i = 1 ; i < fileLines.size() ; i++ ){
            String[] tokenizedLine = fileLines.get(i).split(",");

            String title = tokenizedLine[0];
            String identifierAsString = tokenizedLine[1];
            String checkoutStatus = tokenizedLine[2];
            int checkoutMemberId = Integer.valueOf(tokenizedLine[3]);
            String checkoutDateAsString = tokenizedLine[4];

            ISBN identifier = null;
            try {
                identifier = new ISBN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
            }

            Date checkoutDate = null;
            try {
                checkoutDate = parseCheckoutDateString(checkoutDateAsString);
            } catch ( InvalidContentFileException e ){
                throw e;
            }

            Book nextBook = new Book(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

            filledArrayList.add(nextBook);
        }

        return filledArrayList;
    }

    private static ArrayList<Content> parseDVDFile(ArrayList<String> fileLines){

        ArrayList<Content> filledArrayList = new ArrayList<>();
        for ( int i = 1 ; i < fileLines.size() ; i++ ){
            String[] tokenizedLine = fileLines.get(i).split(",");

            String title = tokenizedLine[0];
            String identifierAsString = tokenizedLine[1];
            String checkoutStatus = tokenizedLine[2];
            int checkoutMemberId = Integer.valueOf(tokenizedLine[3]);
            String checkoutDateAsString = tokenizedLine[4];

            ISBN identifier = null;
            try {
                identifier = new ISBN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
            }

            Date checkoutDate = null;
            try {
                checkoutDate = parseCheckoutDateString(checkoutDateAsString);
            } catch ( InvalidContentFileException e ){
                throw e;
            }

            DVD nextDVD = new DVD(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

            filledArrayList.add(nextDVD);
        }

        return filledArrayList;
    }
    private static ArrayList<Content> parseJournalFile(ArrayList<String> fileLines){
        ArrayList<Content> filledArrayList = new ArrayList<>();
        for ( int i = 1 ; i < fileLines.size() ; i++ ){
            String[] tokenizedLine = fileLines.get(i).split(",");

            String title = tokenizedLine[0];
            String identifierAsString = tokenizedLine[1];
            String checkoutStatus = tokenizedLine[2];
            int checkoutMemberId = Integer.valueOf(tokenizedLine[3]);
            String checkoutDateAsString = tokenizedLine[4];

            ISSN identifier = null;
            try {
                identifier = new ISSN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
            }

            Date checkoutDate = null;
            try {
                checkoutDate = parseCheckoutDateString(checkoutDateAsString);
            } catch ( InvalidContentFileException e ){
                throw e;
            }

            Journal nextJournal = new Journal(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

            filledArrayList.add(nextJournal);
        }

        return filledArrayList;

    }
    private static ArrayList<Content> parseNewspaperFile(ArrayList<String> fileLines){
        ArrayList<Content> filledArrayList = new ArrayList<>();
        for ( int i = 1 ; i < fileLines.size() ; i++ ){
            String[] tokenizedLine = fileLines.get(i).split(",");

            String title = tokenizedLine[0];
            String identifierAsString = tokenizedLine[1];
            String checkoutStatus = tokenizedLine[2];
            int checkoutMemberId = Integer.valueOf(tokenizedLine[3]);
            String checkoutDateAsString = tokenizedLine[4];

            ISSN identifier = null;
            try {
                identifier = new ISSN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
            }

            Date checkoutDate = null;
            try {
                checkoutDate = parseCheckoutDateString(checkoutDateAsString);
            } catch ( InvalidContentFileException e ){
                throw e;
            }

            Newspaper nextNewspaper = new Newspaper(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

            filledArrayList.add(nextNewspaper);
        }

        return filledArrayList;
    }

    private static ArrayList<Content> parseCatchAllFile(ArrayList<String> fileLines){
        ArrayList<Content> filledArrayList = new ArrayList<>();
        for ( int i = 1 ; i < fileLines.size() ; i++ ){
            String[] tokenizedLine = fileLines.get(i).split(",");

            String title = tokenizedLine[0];
            String contentType = tokenizedLine[1];
            String identifierAsString = tokenizedLine[2];
            String checkoutStatus = tokenizedLine[3];
            int checkoutMemberId = Integer.valueOf(tokenizedLine[4]);
            String checkoutDateAsString = tokenizedLine[5];

            Date checkoutDate = null;
            try {
                checkoutDate = parseCheckoutDateString(checkoutDateAsString);
            } catch ( InvalidContentFileException e ){
                throw e;
            }

            /* making the correct content type for each record*/
            if ( contentType.equals("Book") ){
                ISBN identifier = null;
                try {
                    identifier = new ISBN(identifierAsString);
                } catch (InvalidIdentifierException e) {
                    e.printStackTrace();
                    throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
                }

                Book nextBook = new Book(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

                filledArrayList.add(nextBook);

            } else if (contentType.equals("DVD")) {
                ISBN identifier = null;
                try {
                    identifier = new ISBN(identifierAsString);
                } catch (InvalidIdentifierException e) {
                    e.printStackTrace();
                    throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
                }

                DVD nextDVD = new DVD(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

                filledArrayList.add(nextDVD);

            } else if (contentType.equals("Newspaper")) {

                ISSN identifier = null;

                try {
                    identifier = new ISSN(identifierAsString);
                } catch (InvalidIdentifierException e) {
                    e.printStackTrace();
                    throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
                }

                Newspaper nextNewspaper = new Newspaper(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);
                filledArrayList.add(nextNewspaper);

            } else if (contentType.equals("Journal")) {

                ISSN identifier = null;
                try {
                    identifier = new ISSN(identifierAsString);
                } catch (InvalidIdentifierException e) {
                    e.printStackTrace();
                    throw new InvalidContentFileException("Chosen file to parse improperly formatted", e);
                }

                Journal nextJournal = new Journal(title, identifier, checkoutStatus, checkoutMemberId, checkoutDate);

                filledArrayList.add(nextJournal);
            } else {
                throw new InvalidContentFileException("This file features a record with no valid content type");
            }

        }

        return filledArrayList;

    }

    private static Date parseCheckoutDateString(String checkoutDateAsString){
        if ( checkoutDateAsString.equals("NULL") ) return null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date checkoutDate = null;
        try {
            checkoutDate = formatter.parse(checkoutDateAsString);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidContentFileException("This content file has a date in it stored incorrectly");
        }

        return checkoutDate;

    }

    public static void main ( String[] args ){
        Collection myCollection = LibraryFileReader.readFileIntoCollection("LibraryContentFiles/BookList.csv", "Book Collection");

        Content[] contentArray = myCollection.getContentArray();

        myCollection.removeItemByIdentifier(contentArray[0].getIdentifier());

        LibraryFileReader.writeCollectionIntoFile(myCollection, "LibraryContentFiles/BookList2.csv", "Book", "ISBN");

    }

}
