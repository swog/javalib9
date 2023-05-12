package com.javalib9.app.Person;

import java.util.Date;

import com.javalib9.app.Identifier.SSN;
import com.javalib9.app.LibraryFileReader.PersonFileReader;

public class External extends Member {
	public External(
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

	@Override
	public String toString() {
		return String.format(
			"%s,%s,%s,%s,%s,External,%d,%d", 
			
			getName(), 
			getAddress(),
			PersonFileReader.dateToString(getDateOfBirth()),
			getEmail(),
			getSSN().getId(),

			getId(),
			getBalance()
		);
	}
}
