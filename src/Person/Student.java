package src.Person;

import java.util.Date;
import java.util.ArrayList;

import src.Identifier.SSN;

public class Student extends Member {
	protected int professorId;
	protected Professor professor;

	public Student(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social,
		int Id,
		int ProfessorId
	) {
		super(Name, Address, DoB, Email, Social, Id);
		professorId = ProfessorId;
		professor = null;
	}

	public String toString() {
		return "Student";
	}

	public Professor getProfessor() {
		// Professor is valid & the professorId matches the current professor object
		if (professor != null && professor.getId() == professorId) {
			return professor;
		}

		// Professor has changed or never set
		professor = null;

		// Find the professor object
		ArrayList<Person> people = getPeople();
		for (int i = 0; i < people.size(); i++) {
			Person person = people.get(i);
			if (person.isProfessor() && ((Professor)person).getId() == professorId) {
				professor = (Professor)person;
				return professor;
			}
		}

		return null;
	}
}
