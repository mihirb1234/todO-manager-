package com.lcwd.todo.todo_manager.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class Helper {

    // Define a date format pattern
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

        Instant instant=localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date parse =Date.from(instant);
        return parse;
//        return simpleDateFormat.parse(dateStr);
    }
}
