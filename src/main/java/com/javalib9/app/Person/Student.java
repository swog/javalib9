package com.javalib9.app.Person;

import java.util.Date;
import java.util.ArrayList;

import com.javalib9.app.Identifier.SSN;
import com.javalib9.app.LibraryFileReader.PersonFileReader;

public class Student extends Member {
	protected int professorId;
	protected Professor professor;

	public Student() {
	}

	public Student(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social,
		int Id,
		int BalanceDue,
		int ProfessorId
	) {
		super(Name, Address, DoB, Email, Social, Id, BalanceDue);
		professorId = ProfessorId;
		professor = null;
	}

	@Override
	public String toString() {
		return String.format(
			"%s,%s,%s,%s,%s,Student,%d,%d,%d", 
			getName(), 
			getAddress(),
			PersonFileReader.dateToString(getDateOfBirth()),
			getEmail(),
			getSSN().getId(),
			getId(),
			getBalance(),
			professorId
		);
	}

	public void setProfessor(int profId) {
		professorId = profId;
		// Update by default
		// Java doesn't have default parameters???
		getProfessor();
	}

	public void setProfessor(Professor prof) {
		professorId = prof.getId();
		professor = prof;
	}

	public int getProfessorId() {
		return professorId;
	}

	public static Member login(int Id) {
		Member member = getMember(Id);
		if (member == null) {
			return null;
		}

		if (member instanceof Student) {
			return member;
		}

		return null;
	}

	// Initialize all professors and students such that they have accurate professor and student objects.
	public static void initProfessors() throws Exception {
		ArrayList<Person> people = Person.getPeople();
		if (people == null) {
			throw new Exception("Unitialized people list in initProfessors");
		}
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i) instanceof Student) {
				((Student)people.get(i)).getProfessor();
			}
		}
	}

	public Professor getProfessor() throws RuntimeException{
		// Professor is valid & the professorId matches the current professor object
		if (professor != null && professor.getId() == professorId) {
			return professor;
		}

		// Professor has changed or never set
		try {
			professor = (Professor)PersonFileReader.findByMemberId(professorId);
		}
		catch (Exception UninitializedException) {
			professor = null;
			throw(new RuntimeException("Professor not found"));
		}

		// Professor will handle duplicates.
		professor.addStudent(this);

		return professor;
	}
}
