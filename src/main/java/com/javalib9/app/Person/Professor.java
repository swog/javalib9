package com.javalib9.app.Person;

import java.util.ArrayList;
import java.util.Date;

import com.javalib9.app.Identifier.SSN;
import com.javalib9.app.LibraryFileReader.PersonFileReader;

public class Professor extends Member {
	protected ArrayList<Student> students;

	public Professor(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social,
		int BalanceDue,
		int Id
	) {
		super(Name, Address, DoB, Email, Social, Id,BalanceDue);
	}

	public boolean isMyStudent(Student student) { 
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getProfessorId() == getId()) {
				return true;
			}
		}
		return false;
	}

	public void addStudent(Student student) {
		if (isMyStudent(student)) {
			return;
		}

		students.add(student);
		student.setProfessor(this);
	}

	public static Professor login(int Id) {
		Member member = getMember(Id);
		if (member == null) {
			return null;
		}

		if (!member.isProfessor()) {
			return null;
		}

		return (Professor)member;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public String toString() {
		return String.format(
			"Student:\n\tName: %s\n\tAddress: %s\n\tDate of Birth: %s\n\tEmail: %s\n\tSSN: %s\n\tMember Id: %d\n", 
			getName(), 
			getAddress(),
			PersonFileReader.dateToString(getDateOfBirth()),
			getEmail(),
			getSSN().getId(),
			getId()
		);
	}

	@Override
	public boolean isProfessor() {
		return true;
	}
}
