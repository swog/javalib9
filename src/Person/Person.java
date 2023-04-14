package src.Person;

import java.util.Date;
import src.Identifier.SSN;

public abstract class Person {
	private String name;
	private String address;
	private Date dateOfBirth;
	private String email;
	private SSN ssn;
	
	public abstract String toString();

	// Set
	public void setName(String Name) {
		name = Name;
	}

	public void setAddress(String Address) {
		address = Address;
	}

	public void setEmail(String Email) {
		email = Email;
	}

	public void setSSN(SSN Ssn) {
		ssn = Ssn;
	}

	// Get
	public final String getName() {
		return name;
	}

	public final String getAddress() {
		return address;
	}

	public final String getEmail() {
		return email;
	}

	public final SSN getSSN() {
		return ssn;
	}
}
