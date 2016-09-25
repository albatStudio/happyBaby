package com.superkorsuk.happybaby.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Luke on 2016-09-22.
 */

public class Baby {

    private long id;
    private String name;
    private Date birthday;
    private Gender gender;
    private int gestationPeriod;

    public Baby() {
        birthday = new Date();
        gender = Gender.FEMALE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getGestationPeriod() {
        return gestationPeriod;
    }

    public void setGestationPeriod(int gestationPeriod) {
        this.gestationPeriod = gestationPeriod;
    }

    public void setGestationPeriod(int week, int day) {
        this.gestationPeriod = week * 7 + day;
    }

    public String getBirthDayDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return df.format(birthday);
    }

    public Calendar getBirthDayCalendar() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(birthday);

        return cal;
    }

    @Override
    public String toString() {
        return "Baby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + this.getBirthDayDateTime() +
                ", gender=" + gender +
                ", gestationPeriod=" + gestationPeriod +
                '}';
    }
}


