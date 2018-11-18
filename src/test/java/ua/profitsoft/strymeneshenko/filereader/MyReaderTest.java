package ua.profitsoft.strymeneshenko.filereader;

import org.junit.Test;
import ua.profitsoft.strymeneshenko.entity.Contract;
import ua.profitsoft.strymeneshenko.entity.Individual;
import ua.profitsoft.strymeneshenko.entity.InsuredPerson;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class MyReaderTest {
    private static final String PATH_TO_INSURED_PERSONS_TEST = "resources\\insured_persons_for_test.csv";
    private static final String PATH_TO_CONTRACT_TEST = "resources\\contracts_for_test.csv";


    @Test
    public void getInsuredPersonsFromFile() {
        Set<InsuredPerson> persons = null;
        File file = new File(PATH_TO_INSURED_PERSONS_TEST);
        InsuredPerson person1 = new InsuredPerson("Aleksey","Zhykov","14.10.1988",1200,432145671235L);
        try {
            persons = MyReader.getInsuredPersonsFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<InsuredPerson> iterator = persons.iterator();
        assertEquals(person1,iterator.next());
    }

    @Test
    public void getContractsFromFile() {
        File fileInsuredPersons = new File(PATH_TO_INSURED_PERSONS_TEST);
        File fileContracts = new File(PATH_TO_CONTRACT_TEST);
        List<Contract> contracts = new ArrayList<>();
        Set<InsuredPerson> persons = new HashSet<>();
        try {
            contracts = MyReader.getContractsFromFile(fileContracts,fileInsuredPersons);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Individual client = new Individual("Alex","Strymeneshenko","Lybotin Popova st. 7");
        persons.add(new InsuredPerson("Aleksey","Zhykov","14.10.1988",1200,432145671235L));
        Contract contract = new Contract(123332L,"18.11.2018","18.11.2018","18.11.2019", client, persons);
        assertEquals(contract, contracts.get(0));
    }
}