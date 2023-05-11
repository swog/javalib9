package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

public class ProfessorView {

    public static Scene getProfessorLogInScreen(Stage stage){

        VBox mainMenuRoot = new VBox();

        Label memberIdFinderLabel = new Label("Enter Member ID:");
        TextField memberIdFinder = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

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


        mainMenuRoot.getChildren().addAll(memberIdFinderLabel, memberIdFinder, submitButton, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene; 
    } 

    public static Scene getProfessorMainMenu(Stage stage){
        VBox mainMenuRoot = new VBox();

        
        Button getStudentsButton = new Button("Get student list");
        getStudentsButton.setOnAction(new EventHandler<ActionEvent>() {

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



        mainMenuRoot.getChildren().addAll(getStudentsButton, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene;
    }
}
