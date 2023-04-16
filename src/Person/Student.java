package src.Person;

import java.util.Date;
import java.util.ArrayList;

import src.Identifier.SSN;
import src.LibraryFileReader.PersonFileReader;

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

	public Professor getProfessor() {
		// Professor is valid & the professorId matches the current professor object
		if (professor != null && professor.getId() == professorId) {
			return professor;
		}

		// Professor has changed or never set
		professor = (Professor)PersonFileReader.findByMemberId(professorId);

		return null;
	}
}
