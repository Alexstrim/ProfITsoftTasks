package ua.profitsoft.strymeneshenko.service;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.db.dao.ClientDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

import static org.junit.Assert.*;

public class ClientServiceTest {

    @Test
    public void CRUD() throws Exception {
        Client c1 = new Individual("Alexandr", "Shokov", "Kiev, Adb st. 123");

        //Test for create() and read()
        IService<Client> clientService = new ClientService();
        clientService.create(c1);
        Client c2 = clientService.read(c1.getId());
        assertNotNull(c2);
        assertEquals(c1, c2);

        //Test for update()
        c1.setAdress("Lubotin");
        clientService.update(c1);
        c2 = clientService.read(c1.getId());
        assertNotNull(c2);
        assertEquals(c1, c2);

        //Test for delete()
        clientService.delete(c1.getId());
        c2 = clientService.read(c1.getId());
        assertNull(c2);
    }
}