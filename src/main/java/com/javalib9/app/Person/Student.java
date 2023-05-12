package com.javalib9.app.Person;

import java.util.Date;
import java.util.ArrayList;

import com.javalib9.app.Identifier.SSN;
import com.javalib9.app.LibraryFileReader.PersonFileReader;

public class Student extends Member {
	protected int professorId;
	protected Professor professor;

	public Student(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social,
		int BalanceDue,
		int Id,
		int ProfessorId
	) {
		super(Name, Address, DoB, Email, Social, Id,BalanceDue);
		professorId = ProfessorId;
		professor = null;
	}

	public String toString() {
		Professor prof = getProfessor();
		String profName = "Couldn't find";
	
		if (prof != null) {
			profName = prof.getName();
		}

		return String.format(
			"Student:\n\tName: %s\n\tAddress: %s\n\tDate of Birth: %s\n\tEmail: %s\n\tSSN: %s\n\tMember Id: %d\n\tProfessor Id: %d (%s)", 
			getName(), 
			getAddress(),
			PersonFileReader.dateToString(getDateOfBirth()),
			getEmail(),
			getSSN().getId(),
			getId(),
			professorId,
			profName
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

	public Professor getProfessor() {
		// Professor is valid & the professorId matches the current professor object
		if (professor != null && professor.getId() == professorId) {
			return professor;
		}

		// Professor has changed or never set
		try {
			professor = (Professor)PersonFileReader.findByMemberId(professorId);
		}
		catch (Exception uninitializedException) {
			professor = null;
		}

		return professor;
	}
}
