package ua.profitsoft.strymeneshenko.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import static org.junit.Assert.*;

public class ContractTest {

    private Contract contract = null;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outContent));

        Set<InsuredPerson> persons = new HashSet<>();

        persons.add(new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L));
        persons.add(new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800, 1234_4567_1235L));
        persons.add(new InsuredPerson("Roman", "Zayarniy", "14.14.2001", 900, 1236_4547_1235L));

        Client client = new Individual("Alex", "Edison", "Lubotin, Popova street 7");

        Date date = new Date();

        contract = new Contract(12345678, date, date, 24, client, persons);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void allCost() {
        int rezult = contract.allCost(IterationType.LAMBDA_EXPRESSION);
        assertEquals(2400, rezult);
    }

    @Test
    public void printPersonsInAlphaBeticalOrder() {
        contract.printPersonsInAlphaBeticalOrder();
        String exepted = System.lineSeparator() + "InsuredPerson (firstName=Dima, lastName=Pypkin, dateOfBirth=Wed Feb 14 00:00:00 EET 2001, cost=700, identificationNumber=123445671235)" + System.lineSeparator()
                + "InsuredPerson (firstName=Roman, lastName=Zayarniy, dateOfBirth=Thu Feb 14 00:00:00 EET 2002, cost=900, identificationNumber=123645471235)"+ System.lineSeparator() +
                "InsuredPerson (firstName=Yaroslav, lastName=Zhykov, dateOfBirth=Mon Feb 14 00:00:00 EET 2000, cost=800, identificationNumber=123445671235)" + System.lineSeparator();
        assertEquals(exepted, outContent.toString());
    }

    @Test
    public void printPersonsByDateOfBirth() {
        contract.printPersonsByDateOfBirth();
        String exepted = System.lineSeparator() + "InsuredPerson (firstName=Yaroslav, lastName=Zhykov, dateOfBirth=Mon Feb 14 00:00:00 EET 2000, cost=800, identificationNumber=123445671235)" + System.lineSeparator()
                + "InsuredPerson (firstName=Dima, lastName=Pypkin, dateOfBirth=Wed Feb 14 00:00:00 EET 2001, cost=700, identificationNumber=123445671235)"+ System.lineSeparator() +
                "InsuredPerson (firstName=Roman, lastName=Zayarniy, dateOfBirth=Thu Feb 14 00:00:00 EET 2002, cost=900, identificationNumber=123645471235)" + System.lineSeparator();
        assertEquals(exepted, outContent.toString());
    }

    @Test
    public void findPersonByIN() {
        InsuredPerson expectedIp = new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800, 1234_4567_1235L);
        InsuredPerson currentIp = contract.findPersonByIN(1234_4567_1235L);

        assertEquals(expectedIp,currentIp);
    }
}