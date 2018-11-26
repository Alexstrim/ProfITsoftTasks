package ua.profitsoft.strymeneshenko.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class InsuredPersonTest {

    @Test
    public void getInitials() {
        InsuredPerson ip = new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700, 1234_4567_1235L);
        String test = ip.getInitials();
        assertEquals("Pypkin D.", test);
    }
}