package ua.profitsoft.strymeneshenko.db.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.hibernate.InsuredPersonHibernateDAO;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:./src/main/resources/spring-context.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class InsuredPersonHibernateDAOTest {

    @Autowired
    private InsuredPersonHibernateDAO insuredPersonDAO;

    @Test
    public void CRUD() throws Exception{
        InsuredPerson ip1 = new InsuredPerson("Alex", "Pierson", "11.08.1987", 1423, 11111439L);

        //Test for create() and read()
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