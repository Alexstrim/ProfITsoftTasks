package ua.profitsoft.strymeneshenko.db.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:./src/main/resources/spring-context.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ClientDAOTest{

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void CRUD() throws Exception {

        Client c1 = new Individual("Alexandr", "Shokov", "Kiev, Adb st. 123");

        //Test for create() and read()
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