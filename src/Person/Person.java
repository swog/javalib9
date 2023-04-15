package src.Person;

import java.util.Date;
import java.util.ArrayList;
import src.Identifier.SSN;

public abstract class Person {
	protected String name;
	protected String address;
	protected Date dateOfBirth;
	protected String email;
	protected SSN ssn;
	private static ArrayList<Person> people = new ArrayList<>();
	
	public Person() {
	}

	public Person(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social
	) {
		name = Name;
		address = Address;
		dateOfBirth = DoB;
		email = Email;
		ssn = Social;
	}

	public static void addPerson(Person person) {
		people.add(person);
	}

	public static ArrayList<Person> getPeople() {
		return people;
	}

	public static Person getPerson(String Name) {
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).name.equals(Name)) {
				return people.get(i);
			}
		}
		return null;
	}

	// Must define this in subclasses
	public abstract String toString();

	// Member should override this
	public boolean isMember() {
		return false;
	}

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

	public void setSSN(SSN Social) {
		ssn = Social;
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
