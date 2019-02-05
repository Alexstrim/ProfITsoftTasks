package ua.profitsoft.strymeneshenko.data;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import ua.profitsoft.strymeneshenko.util.UtilDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

//Entity InsuredPerson
public class InsuredPerson {

    private long id = 0;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private Date dateOfBirth;
    @NotNull
    private Integer cost;
    @NotNull
    private Long identificationNumber;

    public InsuredPerson() {
    }

    public InsuredPerson(String firstName, String lastName, String date, int cost, long identificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = UtilDate.stringToDate(date);
        this.cost = cost;
        this.identificationNumber = identificationNumber;
    }

    // displays initials
    public String getInitials() {
        return lastName + " " + firstName.substring(0, 1) + ".";
    }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long identificationNumber) {
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

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "InsuredPerson (id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
                + ", dateOfBirth=" + dateOfBirth + ", cost=" + cost + ", identificationNumber=" + identificationNumber + ")";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuredPerson that = (InsuredPerson) o;
        return id == that.id &&
                cost == that.cost &&
                identificationNumber == that.identificationNumber &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, cost, identificationNumber);
    }
}
