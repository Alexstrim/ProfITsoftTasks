package ua.profitsoft.strymeneshenko.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
}
