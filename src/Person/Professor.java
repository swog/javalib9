package src.Person;

import java.util.ArrayList;
import java.util.Date;

import src.Identifier.SSN;
import src.LibraryFileReader.PersonFileReader;

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
