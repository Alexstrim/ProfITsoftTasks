package ua.profitsoft.strymeneshenko.entity;

//Сущность физ. лицо
public class Individual extends Client {

	private String firstName = "";
	private String lastName = "";

	public Individual() {
		super();
		firstName = "Vasya";
		lastName = "Pypkin";
	}

	@Override
	public String toString() {
		return "Individual [firstName=" + firstName + "; lastName=" + lastName + "; adress=" + super.adress + "]";
	}

	public Individual(String firstName, String lastName, String adress) {
		super(adress);
		this.firstName = firstName;
		this.lastName = lastName;
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
}
