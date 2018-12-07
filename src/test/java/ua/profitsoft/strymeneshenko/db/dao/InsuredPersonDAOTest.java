package ua.profitsoft.strymeneshenko.db.dao;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;

import static org.junit.Assert.*;

public class InsuredPersonDAOTest {

    @Test
    public void CRUD() throws Exception{
        InsuredPerson ip1 = new InsuredPerson("Alex", "Pierson", "11.08.1987", 1423, 11111439L);

        //Test for create() and read()
        IDao<InsuredPerson> insuredPersonDAO = new InsuredPersonDAO();
        insuredPersonDAO.create(ip1);
        InsuredPerson ip2 = insuredPersonDAO.read(ip1.getId());
        assertNotNull(ip2);
        assertEquals(ip1, ip2);

        //Test for update()
        ip1.setCost(1111);
        insuredPersonDAO.update(ip1);
        ip2 = insuredPersonDAO.read(ip1.getId());
        assertNotNull(ip2);
        assertEquals(ip1, ip2);

        //Test for delete()
        insuredPersonDAO.delete(ip1.getId());
        ip2 = insuredPersonDAO.read(ip1.getId());
        assertNull(ip2);
    }
}