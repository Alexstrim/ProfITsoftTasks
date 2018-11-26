package ua.profitsoft.strymeneshenko.entity;

import java.util.Objects;

//Abstract entity client
public abstract class Client {

    protected String adress = "";

    public Client() {
    }

    public Client(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(adress, client.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adress);
    }
}
