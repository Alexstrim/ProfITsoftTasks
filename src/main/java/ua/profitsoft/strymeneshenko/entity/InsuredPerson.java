package ua.profitsoft.strymeneshenko.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Сущность застрахованное лицо
public class InsuredPerson{

	private String firstName = "";
	private String lastName = "";
	private Date dateOfBirth = null;
	private int cost = 0;
	private long identificationNumber = 0;

	public long getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(long identificationNumber) {
		this.identificationNumber = identificationNumber;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public InsuredPerson() {
		firstName = "Alexander";
		lastName = "Strymeneshenko";
		dateOfBirth = setDate("14.09.1998");
		cost = 1400;
		identificationNumber = 4213_8734_3428L;
	}

	public InsuredPerson(String firstName, String lastName, String date, int cost, long identificationNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = setDate(date);
		this.cost = cost;
		this.identificationNumber = identificationNumber;
	}

	// устонавливаем дату из строки
	private Date setDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("Введите дату в формате dd.MM.yyyy");
			e.printStackTrace();
		}
		return d;
	}
	
	// выводит ФИ инициалами
	public void printInitials(){
		System.out.printf("%s %s.", lastName, firstName.substring(0, 1));
	}

	@Override
	public String toString() {
		return System.lineSeparator() + "InsuredPerson (firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", cost=" + cost + ", identificationNumber=" + identificationNumber + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InsuredPerson other = (InsuredPerson) obj;
		if (cost != other.cost)
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
