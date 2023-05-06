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

package com.javalib9.app;

import com.javalib9.app.Collection.Collection;
import com.javalib9.app.LibraryFileReader.LibraryFileReader;
import com.javalib9.app.Identifier.Identifier;
import com.javalib9.app.Identifier.ISBN;
import com.javalib9.app.Identifier.ISSN;
import com.javalib9.app.Content.Content;
import com.javalib9.app.Content.Book;
import com.javalib9.app.Content.Newspaper;
import com.javalib9.app.Content.Journal;
import com.javalib9.app.Content.DVD;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javafx.application.Application;


public class ProjectMain extends Application{


    @Override public void start(Stage stage){

        VBox root = new VBox();

        Label helloWorldLabel = new Label("Hello world!");

        root.getChildren().add(helloWorldLabel);

        Scene scene = new Scene(root, 1000, 1000);

        stage.setScene(scene);
        stage.setTitle("Hello world!");
        stage.show();

    }

    public static void projectMain (){
        launch();
    }

}
