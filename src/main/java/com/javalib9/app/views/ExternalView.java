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

public class ExternalView {
    
    public static Scene getExternalMainMenu(Stage stage){
        VBox mainMenuRoot = new VBox();

        
        Label intruderLabel = new Label("INTRUDER ALERT");
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                ProjectMain.generateMainMenu(stage);

            }
        });



        mainMenuRoot.getChildren().addAll(intruderLabel, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene;
    }
}
