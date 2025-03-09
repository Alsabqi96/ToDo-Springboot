package com.codearchitects.todoapp.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//utility class for formatting date and time values
public class DateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    //Formats a LocalDateTime object into a string.
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}





