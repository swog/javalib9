package src.Person;
import java.util.Date;
import src.Identifier.*;

public class Employee extends Person {
	private int empID;
	private String dept;
	private String title;

	public Employee() {
	}

	public Employee(
		int EmpID, 
		String Dept, 
		String Title,
		String Name,
		String Address,
		Date DoB,
		String Email,
		SSN Social
		) {
		super(Name, Address, DoB, Email, Social);
		empID = EmpID;
		dept = Dept;
		title = Title;
	}

	public String toString(){
		return ("Name: " + getName() + "Dept: " + getDept() + "Title: " + getTitle());
	}

	public void setEmpID(int EmpID) {
		this.empID = EmpID;
	}

	public void setDept(String Dept) {
		this.dept = Dept;
	}

	public void setTitle(String Title) {
		this.title = Title;
	}

	public int getEmpID() {
		return empID;
	}

	public String getDept() {
		return dept;
	}

	public String getTitle() {
		return title;
	}

	public static void main(String[] args) {
	}

}
