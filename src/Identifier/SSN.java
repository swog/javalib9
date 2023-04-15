package src.Identifier;

// Social security number for Person class
public class SSN extends Identifier {
	public SSN(String id) {
		// 123-12-1234
		if (id.length() != 11) {
			throw new InvalidIdentifierException("Invalid length, SSN is 11 characters long");
		}
		else {
			this.setId(id);
		}
	}
}
