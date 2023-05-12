

package com.javalib9.app.LibraryFileReader;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.InvalidPathException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;

import com.javalib9.app.Identifier.SSN;
import com.javalib9.app.Person.*;

// Date of birth follows dd/MM/yyyy
// Name,Address,DoB,Email,SSN,Person
// Name,Address,DoB,Email,SSN,Professor,Id,BalanceDue
// Name,Address,DoB,Email,SSN,Student,Id,ProfessorId
// Name,Address,DoB,Email,SSN,External,Id

public class PersonFileReader {
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	public static String dateToString(Date date) {
		return dateFormatter.format(date);
	}

	public static ArrayList<Person> readStudentFile(String fileName) {
		ArrayList<Person> people = new ArrayList<>();
		try {

			Path path = Paths.get(fileName);

			Scanner fileReader = new Scanner(path);
			String line;

			// Skip first line! Formatting for github?
			fileReader.nextLine();

			while (fileReader.hasNextLine()) {
				line = fileReader.nextLine();
				people.add(readPersonFromLine(line));
			}

			fileReader.close();
		} catch (InvalidPathException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return people;
	}

	// write to file
	public static void writePeopleFile(ArrayList<Person> people, String fileName) {
		try {
			//Path path = Paths.get(fileName);
			PrintWriter writer = new PrintWriter(fileName);

			writer.println("Name,Address,DoB,Email,SSN,Type,Id,BalanceDue,ProfessorId");
			for (int i = 0; i < people.size(); i++) {
				writer.println(people.get(i).toString());
			}
			writer.close();
		} catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// remove from people.csv by id
	public static void removePersonById(ArrayList<Person> people, int id) {
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).isMember() && ((Member) people.get(i)).getId() == id) {
				people.remove(i);
				return;
			}
		}
	}

	private static Person readPersonFromLine(String line) {
		try {
			String values[] = line.split(",", 0);
			String name = values[0];
			String address = values[1];
			Date dateOfBirth = dateFormatter.parse(values[2]);
			String email = values[3];
			SSN ssn = new SSN(values[4]);
			String type = values[5];
			switch (type) {
				case "Professor": {
					int memberId = Integer.parseInt(values[6]);
					int memberBalance = Integer.parseInt(values[7]);
					return new Professor(name, address, dateOfBirth, email, ssn, memberId, memberBalance);
				}
				case "Student": {
					int memberId = Integer.parseInt(values[6]);
					int memberBalance = Integer.parseInt(values[7]);
					int professorId = Integer.parseInt(values[8]);
					return new Student(name, address, dateOfBirth, email, ssn, memberId, memberBalance, professorId);
				}
				case "External": {
					int memberId = Integer.parseInt(values[6]);
					int memberBalance = Integer.parseInt(values[7]);
					return new External(name, address, dateOfBirth, email, ssn, memberId, memberBalance);
				}
				// Any other types
			}
		}
		// From SimpleDateFormat
		catch (ParseException e) {
			e.printStackTrace();
		}
		// Given from split?
		catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Member findByMemberId(int memberId) throws Exception {
		ArrayList<Person> people = Person.getPeople();
		if (people == null) {
			throw new Exception("Unitialized people list in findByMemberId");
		}
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).isMember() && ((Member) people.get(i)).getId() == memberId) {
				return (Member) people.get(i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		ArrayList<Person> people = Person.getPeople();

		for (int i = 0; i < people.size(); i++) {
			System.out.println(people.get(i).toString());
		}
	}
}
