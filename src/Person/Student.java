package src.Person;

import java.util.Date;

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
		if (professor != null) {
			return professor;
		}

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
