package src.Person;

import java.util.Date;
import java.util.ArrayList;
import src.Identifier.SSN;

public abstract class Member extends Person {
	protected int id;

	public static Member getMember(int Id) {
		ArrayList<Person> people = getPeople();
		for (int i = 0; i < people.size(); i++) {
			Person person = people.get(i);
			if (person.isMember() && ((Member)person).getId() == Id) {
				return (Member)person;
			}
		}
		return null;
	}

	public Member() {
	}

	public Member(
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social,
		int Id
	) {
		super(Name, Address, DoB, Email, Social);
		id = Id;
	}

	public void setId(int Id) {
		id = Id;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean isMember() {
		return true;
	}

	public static void main(String[] args) {
	}
}
