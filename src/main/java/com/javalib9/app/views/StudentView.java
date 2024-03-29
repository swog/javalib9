package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;
import com.javalib9.app.LibraryFileReader.PersonFileReader;
import com.javalib9.app.Person.Person;
import com.javalib9.app.Person.Student;
import com.javalib9.app.Person.Professor;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

public class StudentView{
    
    public static Scene getStudentLogInScreen(Stage stage){

        VBox mainMenuRoot = new VBox();

        Label memberIdFinderLabel = new Label("Enter Member ID:");
        TextField memberIdFinder = new TextField();

        Button submitButton = new Button("Submit");
        Label submitStatus = new Label("");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){

                String enteredID = memberIdFinder.getText();

                if ( enteredID.equals("")){
                    submitStatus.setText("Please enter your ID above");
                } else {

                int loginAsInteger = 0;
                try {
                    loginAsInteger = Integer.valueOf(enteredID);
                } catch ( NumberFormatException numException){
                    submitStatus.setText("Invalid login, try again");
                    return;
                }

                Student loggedInStudent = (Student)Student.login(loginAsInteger);

                if ( loggedInStudent != null ) stage.setScene(getStudentInformationScreen(stage, loggedInStudent)); 
                else submitStatus.setText("Invalid login, try again");

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

    public static Scene getStudentInformationScreen(Stage stage, Student student){


        VBox studentInfoRoot = new VBox();
        Label professorInformationTitle = new Label("Professor Info:");
        Label professorInformation = new Label("");
        Professor studentProfessor = student.getProfessor();

        System.out.println(student.toString());
        if ( studentProfessor == null )  
        {
            professorInformation.setText("No professor listed");
        } else {
            professorInformation.setText(studentProfessor.toString());
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e){
                stage.setScene(getStudentLogInScreen(stage));

            }
        });

        studentInfoRoot.getChildren().addAll(professorInformationTitle, professorInformation, backButton);
        return new Scene(studentInfoRoot, 1000, 1000);

    }
}
