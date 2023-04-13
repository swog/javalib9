
public abstract class Person {
	private String name;
	private String address;
	//private Date dateOfBirth;
	private String email;
	//private SSN ssn;

	public abstract String toString();

	public void setName(String Name) {
		name = Name;
	}

	public void setAddress(String Address) {
		address = Address;
	}

	public void setEmail(String Email) {
		email = Email;
	}

	public final String getName() {
		return name;
	}

	public final String getAddress() {
		return address;
	}

	public final String getEmail() {
		return email;
	}
}
