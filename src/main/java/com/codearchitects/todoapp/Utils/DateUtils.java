package com.codearchitects.todoapp.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {
    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
