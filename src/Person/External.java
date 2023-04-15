package src.Person;

import java.util.Date;

import src.Identifier.SSN;

public class External extends Member {
	public External(
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
		return "External";
	}
}
