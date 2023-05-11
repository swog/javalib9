package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;

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

public class TechnicianView {
    
    public static Scene getTechnicianMainMenu(Stage stage){
        VBox mainMenuRoot = new VBox();

        Button getBookLocation = new Button("Find Content Location");
        getBookLocation.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){


            }
        }); 
        Button borrowItemButton = new Button("Borrow Item");
        borrowItemButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){


            }
        });
        Button returnItemButton = new Button("Return Item");
        returnItemButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                getReturnItemScreen(stage);

            }
        });
        Button checkOverduesButton = new Button("Check overdues");
        checkOverduesButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){


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

    public static void getReturnItemScreen(Stage stage){

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



        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                stage.setScene(getTechnicianMainMenu(stage));

            }
        });

        returnItemScreenRoot.getChildren().addAll(contentSelectorRoot, identifierFinderLabel, identifierFinder, submitButton, submitButtonStatus, backButton);

        Scene getReturnItemScene = new Scene(returnItemScreenRoot, 1000, 1000);

        stage.setScene(getReturnItemScene);
        stage.setTitle("Return item");
        stage.show();

    }
}
