package com.practice.utility;

import com.practice.constant.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This utility consist two method convertDateTimeToString and
 * convertStringToDateTime.
 *
 * @author Mehul Soni
 */
public class DateFormatterUtility {

    private static DateTimeFormatter KEY_FORMATTER = DateTimeFormatter.ofPattern(Constant.KEY_DATE_FORMAT);
    private static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(Constant.DATETIME_FORMAT);
    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);

    /**
     * This method is used for converting local date time object to string for
     * generating key.
     *
     * @param dateTime
     * @return
     */
    public static String convertDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(KEY_FORMATTER);
    }

    /**
     * This method is used for converting string date to local date time object.
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertStringToDateTime(String date) {
        return LocalDateTime.parse(date, DATETIME_FORMATTER);
    }

    /**
     * This method is used for converting string date to local date object.
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertStringToDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER).atStartOfDay();
    }
}
