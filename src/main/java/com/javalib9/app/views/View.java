package com.javalib9.app.views;

import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class View {
   
    public static ArrayList<RadioButton> getContentTypeButtons(ToggleGroup contentSelectorGroup){

        ArrayList<RadioButton> allButtons = new ArrayList<>();

        RadioButton bookButton = new RadioButton("Book");
        bookButton.setToggleGroup(contentSelectorGroup);
        RadioButton DVDButton = new RadioButton("DVD");
        DVDButton.setToggleGroup(contentSelectorGroup);
        RadioButton journalButton = new RadioButton("Journal");
        journalButton.setToggleGroup(contentSelectorGroup);
        RadioButton newspaperButton = new RadioButton("Newspaper");
        newspaperButton.setToggleGroup(contentSelectorGroup);

        allButtons.add(bookButton);
        allButtons.add(DVDButton);
        allButtons.add(journalButton);
        allButtons.add(newspaperButton);

        return allButtons;
    }
    
    public static Button getBackButton(Stage stage, Scene scene){
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                stage.setScene(scene);

            }
        });

        return backButton;

    }
}
