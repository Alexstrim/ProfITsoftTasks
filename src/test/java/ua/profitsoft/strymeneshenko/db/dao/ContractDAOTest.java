package ua.profitsoft.strymeneshenko.db.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:./src/main/resources/spring-context.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ContractDAOTest {
    @Autowired
    private ClientDAO clientDao;
    @Autowired
    private InsuredPersonDAO insuredPersonDao;
    @Autowired
    private ContractDAO contractDAO;

    @Test
    public void CRUD() throws Exception {
        Client c1 = new Individual("Vasya", "Petrosyan", "Kharkiv, Adb st. 123");
        Client c2 = new Individual("Opa", "Opa", "asasasas, asass");
        clientDao.create(c1);
        clientDao.create(c2);
        InsuredPerson ip1 = new InsuredPerson("Darya", "Pyp", "11.08.1987", 1423, 11572439L);
        insuredPersonDao.create(ip1);
        Set<InsuredPerson> persons = new HashSet<>();
        persons.add(ip1);

        Contract contract = new Contract("03.11.2018", "03.11.2018", "03.11.2019", c1, persons);

        //Test for create() and read()
        contractDAO.create(contract);
        Contract contract2 = contractDAO.read(contract.getNumber());
        assertNotNull(contract2);
        assertEquals(contract, contract2);

        //Test for update()
        contract.setClient(c2);
        contractDAO.update(contract);
        contract2 = contractDAO.read(contract.getNumber());
        assertNotNull(contract2);
        assertEquals(contract, contract2);

        //Test for delete()
        contractDAO.delete(contract.getNumber());
        contract2 = contractDAO.read(contract.getNumber());
        System.out.println(contract2);
        assertNull(contract2);

        clientDao.delete(c1.getId());
        clientDao.delete(c2.getId());
        insuredPersonDao.delete(ip1.getId());
    }
}