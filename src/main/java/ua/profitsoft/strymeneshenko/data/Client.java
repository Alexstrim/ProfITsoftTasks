package ua.profitsoft.strymeneshenko.data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Objects;

//Abstract data client
public class Client {

    protected Long id = 0l;
    @NotEmpty
    @Size(min = 5, max = 40)
    protected String adress;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(adress, client.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adress);
    }
}
