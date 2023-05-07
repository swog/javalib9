package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

public class LibrarianView {
    
    public static Scene getLibrarianMainMenu(Stage stage){
        VBox mainMenuRoot = new VBox();

        Button newCollectionButton = new Button("New collection");
        newCollectionButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){


            }
        });
        Button removeItemFromCollectionButton = new Button ("Remove item from collection");
        removeItemFromCollectionButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){


            }
        });
        Button newMembershipButton = new Button("New membership");
        newMembershipButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){


            }
        });
        Button removeMembershipButton = new Button("Remove membership");
        removeMembershipButton.setOnAction(new EventHandler<ActionEvent>() {

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



        mainMenuRoot.getChildren().addAll(newCollectionButton, removeItemFromCollectionButton, newMembershipButton, removeMembershipButton, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene;
    }
}
