package ua.profitsoft.strymeneshenko.entity;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

//Entity InsuredPerson
public class InsuredPerson {

    private static final Logger LOGGER = Logger.getLogger(InsuredPerson.class);

    private String firstName = "";
    private String lastName = "";
    private Date dateOfBirth = null;
    private int cost = 0;
    private long identificationNumber = 0;

    public InsuredPerson() {
    }

    public InsuredPerson(String firstName, String lastName, String date, int cost, long identificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = setDate(date);
        this.cost = cost;
        this.identificationNumber = identificationNumber;
    }

    //set the date from the string

    private Date setDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            LOGGER.error("Error format of date!",e);
            LOGGER.info("Enter the date in the format dd.MM.yyyy");
        }
        return d;
    }
    // displays initials

    public String getInitials() {
        return lastName + " " + firstName.substring(0, 1) + ".";
    }

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

    @Override
    public String toString() {
        return System.lineSeparator() + "InsuredPerson (firstName=" + firstName + ", lastName=" + lastName
                + ", dateOfBirth=" + dateOfBirth + ", cost=" + cost + ", identificationNumber=" + identificationNumber + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuredPerson that = (InsuredPerson) o;
        return cost == that.cost &&
                identificationNumber == that.identificationNumber &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, cost, identificationNumber);
    }
}
