package ua.profitsoft.strymeneshenko.db.dao;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;

import static org.junit.Assert.*;

public class ClientDAOTest {

    @Test
    public void CRUD() throws Exception {

        Client c1 = new Individual("Alexandr", "Shokov", "Kiev, Adb st. 123");

        //Test for create() and read()
        IDao<Client> clientDAO = new ClientDAO();
        clientDAO.create(c1);
        Client c2 = clientDAO.read(c1.getId());
        assertNotNull(c2);
        assertEquals(c1, c2);

        //Test for update()
        c1.setAdress("Lubotin");
        clientDAO.update(c1);
        c2 = clientDAO.read(c1.getId());
        assertNotNull(c2);
        assertEquals(c1, c2);

        //Test for delete()
        clientDAO.delete(c1.getId());
        c2 = clientDAO.read(c1.getId());
        assertNull(c2);
    }
}