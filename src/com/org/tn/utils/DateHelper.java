package com.org.tn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static Date dateFromStringForm(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date dateFromLongFormat(String inputDate) throws ParseException {

        // Parse input date string into a Date object
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date date = inputDateFormat.parse(inputDate);

        // Format the date into output string format
        DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String outputDateStr = outputDateFormat.format(date).substring(0,10);

        Date outputDate = outputDateFormat.parse(outputDateStr);

        // Print the output string
        return outputDate;
    }
}
