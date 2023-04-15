
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
// Name,Address,DoB,Email,SSN,Student,Id,ProfessorId,

public class PersonFileReader {
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

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

	public static Person readPersonFromLine(String line) {
		try {
			String values[] = line.split(",", 0);
			String name = values[0];
			String address = values[1];
			Date dateOfBirth = dateFormatter.parse(values[2]);
			String email = values[3];
			SSN ssn = new SSN(values[4]);
			String type = values[5];
			switch (type) {
				case "Student": {
					int memberId = Integer.parseInt(values[6]);
					int professorId = Integer.parseInt(values[7]);
					return new Student(name, address, dateOfBirth, email, ssn, memberId, professorId);
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
}