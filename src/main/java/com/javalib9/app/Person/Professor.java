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
		int Id,
		int BalanceDue
	) {
		super(Name, Address, DoB, Email, Social, Id, BalanceDue);
		students = new ArrayList<Student>();
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

	@Override
	public String toString() {
		return String.format(
			"%s,%s,%s,%s,%s,Professor,%d,%d",
			
			getName(), 
			getAddress(),
			PersonFileReader.dateToString(getDateOfBirth()),
			getEmail(),
			getSSN().getId(),

			getId(),
			getBalance()
		);
	}

	@Override
	public boolean isProfessor() {
		return true;
	}
}
