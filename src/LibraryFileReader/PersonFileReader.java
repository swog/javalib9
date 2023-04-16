
package src.LibraryFileReader;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.File;
import java.io.FileNotFoundException;

import src.Identifier.SSN;
import src.Person.*;

// Date of birth follows dd/MM/yyyy
// Name,Address,DoB,Email,SSN,Type
// Name,Address,DoB,Email,SSN,Professor,Id
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
			Scanner fileReader = new Scanner(new File(fileName));
			String line;

			// Skip first line! Formatting for github?
			fileReader.nextLine();

			while (fileReader.hasNextLine()) {
				line = fileReader.nextLine();
				people.add(readPersonFromLine(line));
			}

			fileReader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return people;
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
					return new Professor(name, address, dateOfBirth, email, ssn, memberId);
				}
				case "Student": {
					int memberId = Integer.parseInt(values[6]);
					int professorId = Integer.parseInt(values[7]);
					return new Student(name, address, dateOfBirth, email, ssn, memberId, professorId);
				}
				case "External": {
					int memberId = Integer.parseInt(values[6]);
					return new External(name, address, dateOfBirth, email, ssn, memberId);
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

	public static Member findByMemberId(int memberId) {
		return null;
	}

	public static void main(String[] args) {
		Person.setPeople(PersonFileReader.readStudentFile("LibraryContentFiles/People.csv"));

		ArrayList<Person> people = Person.getPeople();

		for (int i = 0; i < people.size(); i++) {
			System.out.println(people.get(i).toString());
		}
	}
}