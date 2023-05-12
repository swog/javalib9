package com.javalib9.app.Person;

import java.util.Date;
import java.util.ArrayList;
import com.javalib9.app.Identifier.SSN;

public abstract class Member extends Person {
	protected int id;
	protected int balanceDue=0;

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
		int Id,
		int BalanceDue
	) {
		super(Name, Address, DoB, Email, Social);
		id = Id;
		balanceDue = BalanceDue;
	}

	public int getBalance() {
		return balanceDue;
	}

	public void IncBalance()
	{
		balanceDue++;
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
