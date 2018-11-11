package ua.profitsoft.strymeneshenko.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class InsuredPersonTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void printInitials() {
        InsuredPerson ip = new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L);
        ip.printInitials();
        assertEquals("Pypkin D.", outContent.toString());
    }
}