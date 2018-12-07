package ua.profitsoft.strymeneshenko.service;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.db.dao.InsuredPersonDAO;

import static org.junit.Assert.*;

public class InsuredPersonServiceTest {

    @Test
    public void CRUD() throws Exception {
        InsuredPerson ip1 = new InsuredPerson("Alex", "Pierson", "11.08.1987", 1423, 11111439L);

        //Test for create() and read()
        IService<InsuredPerson> insuredPersonService = new InsuredPersonService();
        insuredPersonService.create(ip1);
        InsuredPerson ip2 = insuredPersonService.read(ip1.getId());
        assertNotNull(ip2);
        assertEquals(ip1, ip2);

        //Test for update()
        ip1.setCost(1111);
        insuredPersonService.update(ip1);
        ip2 = insuredPersonService.read(ip1.getId());
        assertNotNull(ip2);
        assertEquals(ip1, ip2);

        //Test for delete()
        insuredPersonService.delete(ip1.getId());
        ip2 = insuredPersonService.read(ip1.getId());
        assertNull(ip2);
    }
}