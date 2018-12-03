package ua.profitsoft.strymeneshenko.data;

import org.junit.Before;
import org.junit.Test;
import ua.profitsoft.strymeneshenko.dict.IterationType;

import java.util.*;
import static org.junit.Assert.*;

public class ContractTest {

    private Contract contract = null;

    @Before
    public void setUp(){

        Set<InsuredPerson> persons = new HashSet<>();

        persons.add(new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L));
        persons.add(new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800, 1234_4567_1235L));
        persons.add(new InsuredPerson("Roman", "Zayarniy", "14.14.2001", 900, 1236_4547_1235L));

        Client client = new Individual("Alex", "Edison", "Lubotin, Popova street 7");

        Date date = new Date();

        contract = new Contract(12345678, date, date, 24, client, persons);
    }

    @Test
    public void allCost() {
        int rezult = contract.allCost(IterationType.LAMBDA_EXPRESSION);
        assertEquals(2400, rezult);
    }

    @Test
    public void sortPersonsInAlphaBeticalOrder() {
        InsuredPerson ip1 = new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L);
        InsuredPerson ip2 = new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800, 1234_4567_1235L);
        InsuredPerson ip3 = new InsuredPerson("Roman", "Zayarniy", "14.14.2001", 900, 1236_4547_1235L);

        Set<InsuredPerson> test = contract.sortPersonsInAlphaBeticalOrder();
        Iterator<InsuredPerson> iterator = test.iterator();
        InsuredPerson person = iterator.next();
        assertEquals(ip1, person);
        person = iterator.next();
        assertEquals(ip3, person);
        person = iterator.next();
        assertEquals(ip2, person);
    }

    @Test
    public void sortPersonsByDateOfBirth() {
        InsuredPerson ip1 = new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L);
        InsuredPerson ip2 = new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800, 1234_4567_1235L);
        InsuredPerson ip3 = new InsuredPerson("Roman", "Zayarniy", "14.14.2001", 900, 1236_4547_1235L);

        Set<InsuredPerson> test = contract.sortPersonsByDateOfBirth();
        Iterator<InsuredPerson> iterator = test.iterator();
        InsuredPerson person = iterator.next();
        assertEquals(ip2, person);
        person = iterator.next();
        assertEquals(ip1, person);
        person = iterator.next();
        assertEquals(ip3, person);
    }

    @Test
    public void findPersonByIN() {
        InsuredPerson expectedIp = new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800, 1234_4567_1235L);
        InsuredPerson currentIp = contract.findPersonByIN(1234_4567_1235L);

        assertEquals(expectedIp,currentIp);
    }
}