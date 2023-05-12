package com.javalib9.app.views;

import com.javalib9.app.ProjectMain;
import com.javalib9.app.Identifier.InvalidIdentifierException;
import com.javalib9.app.Identifier.SSN;
import com.javalib9.app.Person.Librarian;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

//import org.omg.CORBA.DynAnyPackage.Invalid;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LibrarianView {

    public static Scene getLibrarianMainMenu(Stage stage) {
        VBox mainMenuRoot = new VBox();

        Button newCollectionButton = new Button("Add item to collection");
        newCollectionButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                stage.setScene(getAddToCollectionScreen(stage));
                stage.setTitle("Add To Collection");

            }
        });
        Button removeItemFromCollectionButton = new Button("Remove item from collection");
        removeItemFromCollectionButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                stage.setScene(getRemoveFromCollectionScreen(stage));
                stage.setTitle("Remove Item From Collection");

            }
        });
        Button newMembershipButton = new Button("New membership");
        newMembershipButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                stage.setScene(getAddMemberScreen(stage));
                stage.setTitle("Add member");

            }
        });
        Button removeMembershipButton = new Button("Remove membership");
        removeMembershipButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                stage.setScene(getRemoveMemberScreen(stage));
                stage.setTitle("Remove member");

            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ProjectMain.generateMainMenu(stage);

            }
        });

        mainMenuRoot.getChildren().addAll(newCollectionButton, removeItemFromCollectionButton, newMembershipButton,
                removeMembershipButton, backButton);
        Scene mainMenuScene = new Scene(mainMenuRoot, 1000, 1000);

        return mainMenuScene;
    }

    public static Scene getAddToCollectionScreen(Stage stage) {

        VBox addToCollectionRoot = new VBox();

        Label titleFinderLabel = new Label("Enter title");
        TextField titleFinder = new TextField();

        Label contentTypeFinderLabel = new Label("Enter content type");
        ToggleGroup contentTypeFinder = new ToggleGroup();
        ArrayList<RadioButton> allButtons = View.getContentTypeButtons(contentTypeFinder);

        Label identifierLabel = new Label("Enter Identifier");
        TextField identifierFinder = new TextField();

        Button submitButton = new Button("Submit");

        Label submitButtonStatus = new Label("");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            // Function to add content to collection
            public void handle(ActionEvent e) {

                // get selection input
                RadioButton selectedContentTypeButton = ((RadioButton)contentTypeFinder.getSelectedToggle());
                if ( selectedContentTypeButton == null ){
                    submitButtonStatus.setText("Select content type.");
                    return;
                }

                String contentType = selectedContentTypeButton.getText();

                String identifier = identifierFinder.getText();
                if ( identifier.equals("")){
                    submitButtonStatus.setText("Enter Identifier.");
                }

                String title = titleFinder.getText();
                if ( identifier.equals("")){
                    submitButtonStatus.setText("Enter Title.");
                }

                try {
                    Librarian.addItem(identifier, contentType, title);

                    submitButtonStatus.setText("Item added to collection");

                    System.out.printf("%s added to collection\n", identifier);
                } catch (InvalidIdentifierException exception) {
                    submitButtonStatus.setText(exception.getMessage());

                    return;
                }
            }

        });

        Button backButton = View.getBackButton(stage, getLibrarianMainMenu(stage));

        addToCollectionRoot.getChildren().addAll(titleFinderLabel, titleFinder, contentTypeFinderLabel);
        for (RadioButton rb : allButtons) {
            addToCollectionRoot.getChildren().add(rb);
        }

        addToCollectionRoot.getChildren().addAll(identifierLabel, identifierFinder, submitButton, submitButtonStatus,
                backButton);

        Scene addToCollectionScene = new Scene(addToCollectionRoot, 500, 500);
        return addToCollectionScene;
    }

    public static Scene getRemoveFromCollectionScreen(Stage stage) {

        VBox removeFromCollectionRoot = new VBox();

        Label identifierLabel = new Label("Enter Identifier");
        TextField identifierFinder = new TextField();

        Button submitButton = new Button("Submit");

        Label submitButtonStatus = new Label("");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            // Function to remove content from collection
            public void handle(ActionEvent e) {
                String identifier = identifierFinder.getText();

                try {
                    if ( Librarian.removeItem(identifier) ) 
                    {
                        submitButtonStatus.setText(identifier + " removed from collection");
                    } else {
                        submitButtonStatus.setText(identifier + " not found, check your identifier");
                    }


                } catch (InvalidIdentifierException exception) {
                    submitButtonStatus.setText(exception.getMessage());

                    return;
                }
            }

        });

        Button backButton = View.getBackButton(stage, getLibrarianMainMenu(stage));

        removeFromCollectionRoot.getChildren().addAll(identifierLabel, identifierFinder, submitButton,
                submitButtonStatus, backButton);
        return new Scene(removeFromCollectionRoot, 500, 500);
    }

    public static Scene getAddMemberScreen(Stage stage) {

        VBox addMemberScreenRoot = new VBox();

        Button addStudentButton = new Button("Add student");
        addStudentButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                stage.setScene(getAddStudentScreen(stage));
                stage.setTitle("Add Student");
            }

        });
        Button addProfessorButton = new Button("Add professor");
        addProfessorButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                stage.setScene(getAddProfessorScreen(stage));
                stage.setTitle("Add Professor");
            }

        });

        Button backButton = View.getBackButton(stage, getLibrarianMainMenu(stage));
        addMemberScreenRoot.getChildren().addAll(addStudentButton, addProfessorButton, backButton);
        return new Scene(addMemberScreenRoot, 500, 500);
    }

    public static Scene getAddStudentScreen(Stage stage) {

        VBox addStudentScreenRoot = new VBox();

        Label getNameLabel = new Label("Enter Student Name");
        TextField getNameField = new TextField();

        Label getAddressLabel = new Label("Enter Student Address");
        TextField getAddressField = new TextField();

        Label getBirthdayLabel = new Label("Enter Birthday");
        DatePicker getBirthdayField = new DatePicker();

        Label getEmailLabel = new Label("Enter Email");
        TextField getEmailField = new TextField();

        Label getSSN = new Label("Enter SSN");
        TextField getSSNField = new TextField();

        Label getProfID = new Label("Enter Professor Identification");
        TextField getProfIDField = new TextField();

        Label getID = new Label("Enter Student Identification");
        TextField getIDField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            // Function to add student to collection
            @Override
            public void handle(ActionEvent e) {
                String name = getNameField.getText();
                String address = getAddressField.getText();
                Date birthday = java.sql.Date.valueOf(getBirthdayField.getValue());
                String email = getEmailField.getText();
                SSN ssn = new SSN(getSSNField.getText());
                int profID = Integer.parseInt(getProfIDField.getText());
                int id = Integer.parseInt(getIDField.getText());

                try {
                    Librarian.addStudent(name, address, birthday, email, ssn, id, profID);

                    System.out.printf("Student %s added to collection\n", name);
                } catch (InvalidIdentifierException exception) {
                    System.out.printf("Student %s not added to collection\n", name);
                    exception.printStackTrace(System.out);
                }
            }
        });

        Label submitButtonStatus = new Label("");

        Button backButton = View.getBackButton(stage, getAddMemberScreen(stage));

        addStudentScreenRoot.getChildren().addAll(getNameLabel, getNameField, getAddressLabel, getAddressField,
                getBirthdayLabel, getBirthdayField, getEmailLabel, getEmailField, getSSN, getSSNField, getProfID,
                getProfIDField, getID, getIDField, submitButton, submitButtonStatus, backButton);

        return new Scene(addStudentScreenRoot, 500, 500);
    }

    public static Scene getAddProfessorScreen(Stage stage) {

        VBox addProfessorScreenRoot = new VBox();

        Label getNameLabel = new Label("Enter Student Name");
        TextField getNameField = new TextField();

        Label getAddressLabel = new Label("Enter Student Address");
        TextField getAddressField = new TextField();

        Label getBirthdayLabel = new Label("Enter Birthday");
        DatePicker getBirthdayField = new DatePicker();

        Label getEmailLabel = new Label("Enter Email");
        TextField getEmailField = new TextField();

        Label getSSN = new Label("Enter SSN");
        TextField getSSNField = new TextField();

        Label getID = new Label("Enter Professor Identification");
        TextField getIDField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            // Function to add professor to collection
            @Override
            public void handle(ActionEvent e) {
                String name = getNameField.getText();
                String address = getAddressField.getText();
                Date birthday = java.sql.Date.valueOf(getBirthdayField.getValue());
                String email = getEmailField.getText();
                SSN ssn = new SSN(getSSNField.getText());
                int id = Integer.parseInt(getIDField.getText());

                try {
                    Librarian.addProfessor(name, address, birthday, email, ssn, id);

                    System.out.printf("Professor %s added to collection\n", name);
                } catch (InvalidIdentifierException exception) {
                    System.out.printf("Professor %s not added to collection\n", name);
                    exception.printStackTrace(System.out);
                }
            }
        });

        Label submitButtonStatus = new Label("");

        Button backButton = View.getBackButton(stage, getAddMemberScreen(stage));

        addProfessorScreenRoot.getChildren().addAll(getNameLabel, getNameField, getAddressLabel, getAddressField,
                getBirthdayLabel, getBirthdayField, getEmailLabel, getEmailField, getSSN, getSSNField, getID,
                getIDField, submitButton, submitButtonStatus, backButton);

        return new Scene(addProfessorScreenRoot, 500, 500);
    }

    public static Scene getRemoveMemberScreen(Stage stage) {
        VBox removeMemberRoot = new VBox();

        Label getIDLabel = new Label("Enter ID of Member to Remove");
        TextField getIDField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            // Function to remove member from collection
            @Override
            public void handle(ActionEvent e) {
                int id = Integer.parseInt(getIDField.getText());

                try {
                    Librarian.removeMember(id);

                    System.out.printf("Member %d removed from collection\n", id);
                } catch (InvalidIdentifierException exception) {
                    System.out.printf("Member %d not removed from collection\n", id);
                    exception.printStackTrace(System.out);
                }
            }
        });
        Label submitButtonStatus = new Label("");

        Button backButton = View.getBackButton(stage, getLibrarianMainMenu(stage));

        removeMemberRoot.getChildren().addAll(getIDLabel, getIDField, submitButton, submitButtonStatus, backButton);

        return new Scene(removeMemberRoot, 500, 500);

    }
}
