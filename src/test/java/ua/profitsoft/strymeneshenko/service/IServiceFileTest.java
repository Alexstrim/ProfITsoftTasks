package ua.profitsoft.strymeneshenko.service;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class IServiceFileTest {

    @Test
    public void read() throws Exception {
        Set<InsuredPerson> persons = new HashSet<>();
        Individual client = new Individual("Alex","Strymeneshenko","Lybotin Popova st. 7");

        persons.add(new InsuredPerson("Denys","Tomson","01.04.1985",1000,432145673335L));
        persons.add(new InsuredPerson("Alexandr","Pypkin","12.10.1989",1100,432145671336L));
        Contract contract = new Contract(123332L,"18.11.2018","18.11.2018","18.11.2019", client, persons);

        IServiceFile<Contract> contractIServiceFile = new ContractServiceFile();
        Contract contractFile = contractIServiceFile.read(123332L);
        assertEquals(contract,contractFile);
    }
}