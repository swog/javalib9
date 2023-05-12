package com.javalib9.app.views;

import java.util.ArrayList;

import com.javalib9.app.ProjectMain;
import com.javalib9.app.Person.Professor;
import com.javalib9.app.Person.Student;

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
        Label submitStatus = new Label("");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                String enteredID = memberIdFinder.getText();

                
                Professor loggedInUser = Professor.login(Integer.valueOf(enteredID));
                if ( loggedInUser != null ){
                    stage.setScene(getProfessorMainMenu(stage, loggedInUser));
                } else {
                    submitStatus.setText("Invalid login, try again.");
                }

            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                ProjectMain.generateMainMenu(stage);

            }
        });


        mainMenuRoot.getChildren().addAll(memberIdFinderLabel, memberIdFinder, submitButton, submitStatus, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene; 
    } 

    public static Scene getProfessorMainMenu(Stage stage, Professor prof){
        VBox mainMenuRoot = new VBox();

        
        Button getStudentsButton = new Button("Get student list");
        getStudentsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                stage.setScene(getStudentListScreen(stage, prof));
                stage.setTitle("Student list");
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

    public static Scene getStudentListScreen(Stage stage, Professor prof){

        VBox studentListScreenRoot = new VBox();

        ArrayList<Student> studentList = prof.getStudents();

        if ( studentList == null ) {
            studentListScreenRoot.getChildren().add(new Label("No students"));
        }
        else {
            for ( Student s : studentList ){
             studentListScreenRoot.getChildren().add(new Label(s.toString()));
            }

        }

        Button backButton = View.getBackButton(stage, getProfessorMainMenu(stage, prof));
        studentListScreenRoot.getChildren().add(backButton);

        return new Scene(studentListScreenRoot, 500, 500);
    }

}
