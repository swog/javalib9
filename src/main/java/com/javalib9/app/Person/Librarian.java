package com.javalib9.app.Person;
import java.util.Date;
import com.javalib9.app.Identifier.*;


public class Librarian extends Employee {

	public Librarian(){
	}
	
	public Librarian(
		int EmpID,
		String Dept,
		String Title,
		String Name,
		String Address,
		Date Dob,
		String Email,
		SSN Social
	) {
		super(EmpID, Dept, Title, Name, Address, Dob, Email, Social);
	}

	public String toString() {
		return "";
	}

	public static void main(String[] args) {
	}

}