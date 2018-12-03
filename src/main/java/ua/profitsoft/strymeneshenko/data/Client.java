package ua.profitsoft.strymeneshenko.data;

import java.util.Objects;

//Abstract data client
public abstract class Client {

    protected long id = 0;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
