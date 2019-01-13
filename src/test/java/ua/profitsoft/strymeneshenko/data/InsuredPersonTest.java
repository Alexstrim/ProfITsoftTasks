package ua.profitsoft.strymeneshenko.data;

import org.junit.Test;

import static org.junit.Assert.*;


public class InsuredPersonTest {

    @Test
    public void getInitials() {
        InsuredPerson ip = new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L);
        String test = ip.getInitials();
        assertEquals("Pypkin D.", test);
    }
}