package com.javalib9.app.Person;

import java.util.Date;
import java.util.ArrayList;
import com.javalib9.app.Identifier.SSN;

public abstract class Person {
	protected String name;
	protected String address;
	protected Date dateOfBirth;
	protected String email;
	protected SSN ssn;
	protected static ArrayList<Person> people = null;

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

	public static void setPeople(ArrayList<Person> People) {
		people = People;
	}

	public static void addPerson(Person person) {
		for (int i = 0; i < people.size(); i++) {
			if (person instanceof Member && 
				people.get(i) instanceof Member && 
				((Member)people.get(i)).getId() == ((Member)person).getId()
			) {
				System.out.println("Removed person confliction during removal!");
				people.remove(i);
			}
		}

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

	public boolean isProfessor() {
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

	public void setDateOfBirth(Date DoB) {
		dateOfBirth = DoB;
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

	public final Date getDateOfBirth() {
		return dateOfBirth;
	}
}
