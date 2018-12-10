package ua.profitsoft.strymeneshenko.service;

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
import ua.profitsoft.strymeneshenko.db.dao.ClientDAO;
import ua.profitsoft.strymeneshenko.db.dao.ContractDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.db.dao.InsuredPersonDAO;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:./src/main/resources/spring-context.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ContractServiceTest {
    @Autowired
    private IService<Client> clientService;
    @Autowired
    private IService<InsuredPerson> insuredPersonService;
    @Autowired
    private IService<Contract> contractService;

    @Test
    public void CRUD() throws Exception {
        Client c1 = new Individual("Vasya", "Petrosyan", "Kharkiv, Adb st. 123");
        Client c2 = new Individual("Opa", "Opa", "asasasas, asass");
        clientService.create(c1);
        clientService.create(c2);
        InsuredPerson ip1 = new InsuredPerson("Darya", "Pyp", "11.08.1987", 1423, 11572439L);
        insuredPersonService.create(ip1);
        Set<InsuredPerson> persons = new HashSet<>();
        persons.add(ip1);

        Contract contract = new Contract("03.11.2018", "03.11.2018", "03.11.2019", c1, persons);

        //Test for create() and read()
        contractService.create(contract);
        Contract contract2 = contractService.read(contract.getNumber());
        assertNotNull(contract2);
        assertEquals(contract, contract2);

        //Test for update()
        contract.setClient(c2);
        contractService.update(contract);
        contract2 = contractService.read(contract.getNumber());
        contract2.setPersons(contract2.sortPersonsInAlphaBeticalOrder());
        assertNotNull(contract2);
        assertEquals(contract, contract2);

        //Test for delete()
        contractService.delete(contract.getNumber());
        contract2 = contractService.read(contract.getNumber());
        assertNull(contract2);

        clientService.delete(c1.getId());
        clientService.delete(c2.getId());
        insuredPersonService.delete(ip1.getId());
    }
}