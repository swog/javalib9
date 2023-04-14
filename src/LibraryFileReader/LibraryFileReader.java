package src.LibraryFileReader;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

import src.Collection.Collection;
import src.Content.Content;
import src.Content.Book;
import src.Content.DVD;
import src.Content.Newspaper;
import src.Content.Journal;
import src.Identifier.Identifier;
import src.Identifier.ISBN;
import src.Identifier.ISSN;
import src.Identifier.InvalidIdentifierException;
/*
 * STATIC METHODS
 * > Collection readFileIntoCollection(String fileName)
 * >> searches for an exact file and puts everything from that file into a collection
 * */

public class LibraryFileReader {

    public static Collection readAllFilesIntoCompleteCollection(){
        return null;
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

        if ( tokenizedFirstLine[0].equals("Book") ){
            contentForNewCollection = parseBookFile(fileLines);
        } else if ( tokenizedFirstLine[0].equals("DVD") ){
            contentForNewCollection = parseDVDFile(fileLines);
        } else if ( tokenizedFirstLine[0].equals("Newspaper") ){
            contentForNewCollection = parseNewspaperFile(fileLines);
        } else if ( tokenizedFirstLine[0].equals("Journal") ){
            contentForNewCollection = parseJournalFile(fileLines);
        }

        return new Collection(newCollectionName, contentForNewCollection, "Identifier");
    }

    private static ArrayList<Content> parseBookFile(ArrayList<String> fileLines){

        ArrayList<Content> filledArrayList = new ArrayList<>();
        for ( int i = 1 ; i < fileLines.size() ; i++ ){
            String[] tokenizedLine = fileLines.get(i).split(",");

            String title = tokenizedLine[0];
            String identifierAsString = tokenizedLine[1];
            String checkoutStatus = tokenizedLine[2];

            ISBN identifier = null;
            try {
                identifier = new ISBN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                return null;
            }

            Book nextBook = new Book(title, identifier, checkoutStatus);

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

            ISBN identifier = null;
            try {
                identifier = new ISBN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                return null;
            }

            DVD nextDVD = new DVD(title, identifier, checkoutStatus);

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

            ISSN identifier = null;
            try {
                identifier = new ISSN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                return null;
            }

            Journal nextJournal = new Journal(title, identifier, checkoutStatus);

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

            ISSN identifier = null;
            try {
                identifier = new ISSN(identifierAsString);
            } catch (InvalidIdentifierException e) {
                e.printStackTrace();
                return null;
            }

            Newspaper nextNewspaper = new Newspaper(title, identifier, checkoutStatus);

            filledArrayList.add(nextNewspaper);
        }

        return filledArrayList;
    }

}
