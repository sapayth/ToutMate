package com.nullpointers.toutmate.Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private Date date = new Date();

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

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

    public long getCurrentDate(){
        return getDateInUnix(day+"/"+(month+1)+"/"+year);
    }
}
