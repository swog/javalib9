

package com.javalib9.app;

import com.javalib9.app.Collection.Collection;
import com.javalib9.app.LibraryFileReader.LibraryFileReader;
import com.javalib9.app.views.ExternalView;
import com.javalib9.app.views.LibrarianView;
import com.javalib9.app.views.ProfessorView;
import com.javalib9.app.views.StudentView;
import com.javalib9.app.views.TechnicianView;
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
import javafx.scene.layout.HBox;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler; 


public class ProjectMain extends Application{


    @Override public void start(Stage stage){

        generateMainMenu(stage);

    }

    public static void projectMain (){
        launch();
    }

    public static void generateMainMenu(Stage stage){

        Stage mainMenuStage = stage; 

        VBox mainMenuRoot = new VBox();


        Label directionLabel = new Label("Who are you?");

        Button librarianButton = new Button("Librarian");
        librarianButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                mainMenuStage.setScene(LibrarianView.getLibrarianMainMenu(stage));

            }
        });

        Button technicianButton = new Button("Technician");
        technicianButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){

                mainMenuStage.setScene(TechnicianView.getTechnicianMainMenu(stage));

            }
        });

        Button professorButton = new Button("Professor");
        professorButton.setOnAction(new EventHandler<ActionEvent> (){

            @Override
            public void handle(ActionEvent e){
                mainMenuStage.setScene(ProfessorView.getProfessorMainMenu(mainMenuStage));
            }
        });

        Button studentButton = new Button("Student");
        studentButton.setOnAction(new EventHandler<ActionEvent> (){

            @Override
            public void handle(ActionEvent e){
                mainMenuStage.setScene(StudentView.getStudentLogInScreen(stage));
            }
        });

        Button externalButton = new Button("Non-member");
        externalButton.setOnAction(new EventHandler<ActionEvent> (){

            @Override
            public void handle(ActionEvent e){

                mainMenuStage.setScene(ExternalView.getExternalMainMenu(mainMenuStage));

            }
        });

        mainMenuRoot.getChildren().addAll(directionLabel, librarianButton, technicianButton, professorButton, studentButton, externalButton);

        Scene mainMenuScene = new Scene(mainMenuRoot, 500, 500);

        mainMenuStage.setScene(mainMenuScene);
        mainMenuStage.setTitle("Main menu");
        mainMenuStage.show();
    }
}
