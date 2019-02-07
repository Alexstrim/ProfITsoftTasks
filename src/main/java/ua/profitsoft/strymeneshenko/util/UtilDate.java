package ua.profitsoft.strymeneshenko.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UtilDate {
    private static final Logger LOGGER = Logger.getLogger(UtilDate.class);

    private UtilDate() {
    }

    //Convert string to date
    public static Date stringToDate(String data) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd.MM.yyyy");
        try {
            date = sdf.parse(data);
            date = new java.sql.Date(date.getTime() + 86400000);
        } catch (ParseException e) {
            LOGGER.error("Not valid date", e);
        }
        return date;
    }

    //Convert string to date with format
    public static Date stringToDate(String data, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(format);
        try {
            date = sdf.parse(data);
            date = new java.sql.Date(date.getTime() + 86400000);
        } catch (ParseException e) {
            LOGGER.error("Not valid date", e);
        }
        return date;
    }

    // The method changes the date to the number of months (passed in the month parameter) ahead or back.
    // For example:
    // When adding a starting date to the contract, we automatically calculate
    // the expiration date of the contract

    public static Date editDate(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String d = sdf.format(date);
        try {
            calendar.setTime(sdf.parse(d));
        } catch (ParseException e) {
            LOGGER.error("Error editDate", e);
        }
        calendar.add(Calendar.MONTH, month);
        d = sdf.format(calendar.getTime());
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            LOGGER.error("Error editDate", e);
        }
        return date;
    }

    //The method allows you to display the date in the desired format.

    public static String parseDateToFormat(Date date, String format){
        DateFormat outFormatter = new SimpleDateFormat(format);
        String output = outFormatter.format(date);
        return output;
    }

    public static Date generateRandomDate(){
        // Get a new random instance, seeded from the clock
        Random rnd = new Random();

        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date
        return new Date(ms);
    }
}
