package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;
import com.javalib9.app.Collection.DueCheck;
import com.javalib9.app.Identifier.InvalidIdentifierException;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

import java.util.ArrayList;

import com.javalib9.app.Person.technician;

public class TechnicianView {
    
    public static Scene getTechnicianMainMenu(Stage stage){
        VBox mainMenuRoot = new VBox();

        Button getBookLocation = new Button("Find Content Location");
        getBookLocation.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                stage.setScene(getFindContentLocationScreen(stage));
                stage.setTitle("Find Content Location");

            }
        }); 
        Button borrowItemButton = new Button("Borrow Item");
        borrowItemButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                stage.setScene(getBorrowItemScreen(stage));
                stage.setTitle("Borrow Item");

            }
        });
        Button returnItemButton = new Button("Return Item");
        returnItemButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                stage.setScene(getReturnItemScreen(stage));
                stage.setTitle("Return item");

            }
        });
        Button checkOverduesButton = new Button("Check Dues");
        checkOverduesButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                stage.setScene(getCheckDuesScreen(stage));
                stage.setTitle("Check Dues");

            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                ProjectMain.generateMainMenu(stage);

            }
        });



        mainMenuRoot.getChildren().addAll(getBookLocation, borrowItemButton, returnItemButton, checkOverduesButton, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene;
    }

    public static Scene getFindContentLocationScreen(Stage stage){
        Label contentTypeFinderLabel = new Label("Enter content type");
        ToggleGroup contentTypeFinder = new ToggleGroup();
        ArrayList<RadioButton> allButtons = View.getContentTypeButtons(contentTypeFinder);

        Label identifierLabel = new Label("Enter Identifier");
        TextField identifierFinder = new TextField();

        Button submitButton = new Button("Submit");
        Label submitButtonStatus = new Label("");

        submitButton.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent e){

                String contentEntered = ((RadioButton)contentTypeFinder.getSelectedToggle()).getText();
                String identifierEntered = identifierFinder.getText();

                if ( identifierEntered.equals("")){
                    submitButtonStatus.setText("Please enter identifier before proceeding");

                    return;
                }

                String foundLocationAsString = null;
                try{

                    foundLocationAsString = technician.findLocation(contentEntered, identifierEntered);
                } catch (InvalidIdentifierException iie){
                    submitButtonStatus.setText("Invalid identifier, try again");
                    iie.printStackTrace(System.out);
                    return;
                }
                Stage placementStage = new Stage();

                VBox placementRoot = new VBox( new Label(foundLocationAsString));

                Scene placementScene = new Scene(placementRoot, 300, 50);

                placementStage.setScene(placementScene);
                placementStage.setTitle("Here's your placement!");
                placementStage.show();
            }

        });


        Button backButton = View.getBackButton(stage, getTechnicianMainMenu(stage));

        VBox findContentLocationRoot = new VBox(contentTypeFinderLabel);
        for ( RadioButton b : allButtons ){
            findContentLocationRoot.getChildren().add(b);
        }
        findContentLocationRoot.getChildren().addAll(identifierLabel, identifierFinder, submitButton, submitButtonStatus, backButton);

        return new Scene(findContentLocationRoot, 500, 500);

    }

    public static Scene getBorrowItemScreen(Stage stage){

        Label contentTypeFinderLabel = new Label("Enter content type");
        ToggleGroup contentTypeFinder = new ToggleGroup();
        ArrayList<RadioButton> allButtons = View.getContentTypeButtons(contentTypeFinder);


        Label identifierLabel = new Label("Enter Identifier");
        TextField identifierFinder = new TextField();

        Label memberLabel = new Label("Enter MemberID");
        TextField memberFinder = new TextField();

        Button submitButton = new Button("Submit");
        Label submitButtonStatus = new Label("");
        submitButton.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent e){

                String selectedContentType = ((RadioButton)contentTypeFinder.getSelectedToggle()).getText();
                String enteredIdentifier = identifierFinder.getText();
                String enteredMemberID = memberFinder.getText();

                System.out.printf("%s | %s | %s\n", selectedContentType, enteredIdentifier, enteredMemberID);

                try{

                    technician.borrowItem(selectedContentType, enteredIdentifier, Integer.parseInt(enteredMemberID));
                    submitButtonStatus.setText("Successfully borrowed item of identifier " + enteredIdentifier);
                } catch (InvalidIdentifierException exception){
                    submitButtonStatus.setText(exception.getMessage());

                    return;
                }




            }

        });


        Button backButton = View.getBackButton(stage, getTechnicianMainMenu(stage));

        VBox borrowItemRoot = new VBox(contentTypeFinderLabel);
        for ( RadioButton b : allButtons ){
            borrowItemRoot.getChildren().add(b);
        }
        borrowItemRoot.getChildren().addAll(identifierLabel, identifierFinder, memberLabel, memberFinder, submitButton, submitButtonStatus, backButton);


        return new Scene(borrowItemRoot, 500, 500);
    }
    public static Scene getReturnItemScreen(Stage stage){

        VBox returnItemScreenRoot = new VBox();

        ToggleGroup contentSelectorGroup = new ToggleGroup();
        RadioButton bookButton = new RadioButton("Book");
        bookButton.setToggleGroup(contentSelectorGroup);
        RadioButton DVDButton = new RadioButton("DVD");
        DVDButton.setToggleGroup(contentSelectorGroup);
        RadioButton journalButton = new RadioButton("Journal");
        journalButton.setToggleGroup(contentSelectorGroup);
        RadioButton newspaperButton = new RadioButton("Newspaper");
        newspaperButton.setToggleGroup(contentSelectorGroup);

        HBox contentSelectorRoot = new HBox();
        contentSelectorRoot.getChildren().addAll(bookButton, DVDButton, journalButton, newspaperButton);

        Label identifierFinderLabel = new Label("Enter identifier of content below");
        TextField identifierFinder = new TextField();

        Button submitButton = new Button("Submit");
        Label submitButtonStatus = new Label("");
        submitButton.setOnAction( new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                String selectedContentType = ((RadioButton)contentSelectorGroup.getSelectedToggle()).getText();
                String writtenIdentifier = identifierFinder.getText(); 


                if ( technician.returnItem(selectedContentType, writtenIdentifier) ) submitButtonStatus.setText("Success!");
                else submitButtonStatus.setText("Failed to turn item in. Ensure your identifier is correct and try again.");

            }
        });


        


        Button backButton = View.getBackButton(stage, getTechnicianMainMenu(stage));

        returnItemScreenRoot.getChildren().addAll(contentSelectorRoot, identifierFinderLabel, identifierFinder, submitButton, submitButtonStatus, backButton);

        Scene getReturnItemScene = new Scene(returnItemScreenRoot, 1000, 1000);


        return getReturnItemScene;
    }
    
    public static Scene getCheckDuesScreen(Stage stage){

        VBox checkDuesRoot = new VBox();

        DueCheck dueCheck = new DueCheck();
        ArrayList<String[]> dueInformation = dueCheck.newCheckOverdues();

        if ( dueInformation.size() == 0){
            checkDuesRoot.getChildren().add(new Label("No items checked out"));
        }

        for ( String[] s : dueInformation){
            checkDuesRoot.getChildren().add(new Label(s[0] + " | " + s[1] + " | " + s[2] + " | " + s[3] + " | "));
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                stage.setScene(getTechnicianMainMenu(stage));

            }
        });

        checkDuesRoot.getChildren().addAll(backButton);

        return new Scene(checkDuesRoot, 500, 500);
    }
}
