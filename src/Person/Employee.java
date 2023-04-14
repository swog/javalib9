package src.Person;

public class Employee extends Person {
	private int empID;
	private String dept;
	private String title;

	public Employee() {
	}

	public Employee(int EmpID, String Dept, String Title) {
		empID = EmpID;
		dept = Dept;
		title = Title;
	}

	public String toString() {
		return "";
	}

	public void setEmpID(int EmpID) {
		empID = EmpID;
	}

	public void setDept(String Dept) {
		dept = Dept;
	}

	public void setTitle(String Title) {
		title = Title;
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
