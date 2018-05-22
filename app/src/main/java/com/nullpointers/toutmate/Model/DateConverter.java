package com.nullpointers.toutmate.Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private Date date = null;

    public String getDateInString(long unixDate){
        date = new Date(unixDate*1000L);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        return dateFormat.format(date);
    }

    public long getDateInUnix(String date){
        long unixTime = 0;
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        try {
            unixTime = dateFormat.parse(date).getTime();
            unixTime = unixTime / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unixTime;
    }
}
