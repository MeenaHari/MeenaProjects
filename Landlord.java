package landLordExample.Rest.main;


public class Landlord {
	private String firstName;
	private String lastName;
	private boolean trusted;
	
	public Landlord(){};
	
	public Landlord(String firstName,String lastName){
		this.setFirstName(firstName);
		this.setLastName(lastName);
	};
	
	public Landlord(String firstName,String lastName,boolean trusted){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setTrusted(trusted);
		
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getTrusted() {
		return trusted;
	}

	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}
}
