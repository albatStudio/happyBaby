package com.superkorsuk.happybaby.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Luke on 2016-09-22.
 */

public class BabyDo {
    // common
    protected long id;
    protected String note;
    protected Date issueDate = new Date();
    protected BabyDoType babyDoType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDateToDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return df.format(issueDate);
    }

    public BabyDoType getBabyDoType() {
        return babyDoType;
    }

    public void setBabyDoType(BabyDoType babyDoType) {
        this.babyDoType = babyDoType;
    }

    public Calendar getDateCalendar() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(issueDate);

        return cal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
