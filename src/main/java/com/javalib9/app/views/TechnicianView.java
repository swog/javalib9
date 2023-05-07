package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

public class TechnicianView {
    
    public static Scene getTechnicianMainMenu(Stage stage){
        VBox mainMenuRoot = new VBox();

        Button getBookLocation = new Button("Find book location");
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
}
