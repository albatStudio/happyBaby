package com.superkorsuk.happybaby.models;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Luke on 2016-09-22.
 */

public class BabyDo {
    // common
    protected String note;
    protected Date date;
    protected BabyDoType babyDoType;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Calendar getDateCalendar() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);

        return cal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
