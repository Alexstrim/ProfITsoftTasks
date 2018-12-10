package ua.profitsoft.strymeneshenko.data;

import java.util.Objects;

//Entity individual
public class Individual extends Client {

    private String firstName = "";
    private String lastName = "";

    public Individual() {
    }

    @Override
    public String toString() {
        return "Individual [id="+ super.id + ", firstName=" + firstName + "; lastName=" + lastName + "; adress=" + super.adress + "]";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
