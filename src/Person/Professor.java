package src.Person;

import java.util.ArrayList;
import java.util.Date;

import src.Identifier.SSN;

public class Professor extends Member {
	protected ArrayList<Student> students;

	public Professor(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social,
		int Id
	) {
		super(Name, Address, DoB, Email, Social, Id);
	}

	public String toString() {
		return "Professor";
	}

	@Override
	public boolean isProfessor() {
		return true;
	}
}
