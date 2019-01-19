package ua.profitsoft.strymeneshenko.db.dao;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.file.ConractsFileDAO;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConractsFileDAOTest {

    @Test
    public void read() throws Exception {
        Set<InsuredPerson> persons = new HashSet<>();
        Individual client = new Individual("Alex","Strymeneshenko","Lybotin Popova st. 7");

        persons.add(new InsuredPerson("Denys","Tomson","01.04.1985",1000,432145673335L));
        persons.add(new InsuredPerson("Alexandr","Pypkin","12.10.1989",1100,432145671336L));
        Contract contract = new Contract(123332L,"18.11.2018","18.11.2018","18.11.2019", client, persons);

        IDao<Contract> daoFile= new ConractsFileDAO();
        Contract contractFile = daoFile.read(123332L);
        assertEquals(contract,contractFile);
    }
}