package com.javalib9.app;

import com.javalib9.app.Person.*;
import com.javalib9.app.LibraryFileReader.*;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        // Initialize the people list
        // - A static array of all people in the Person class
        Person.setPeople(PersonFileReader.readStudentFile("LibraryContentFiles/People.csv"));

        ProjectMain.projectMain();
    }
}
