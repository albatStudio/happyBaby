package com.superkorsuk.happybaby.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@DatabaseTable(tableName = Baby.TABLE_NAME)
public class Baby {
    public static final String TABLE_NAME = "babies";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss", canBeNull = false)
    private Date birthday;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private Gender gender;

    private int gestationPeriod;

    public Baby() {
        birthday = new Date();
        gender = Gender.FEMALE;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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


    public String getBirthDayYear() {

        return String.valueOf(getBirthDayToCalendar().get(Calendar.YEAR));
    }

    public String getBirthDayMonth() {
        return String.valueOf(getBirthDayToCalendar().get(Calendar.MONTH) + 1);
    }
    public String getBirthDayDay() {
        return String.valueOf(getBirthDayToCalendar().get(Calendar.DATE));
    }

    public Calendar getBirthDayToCalendar() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(birthday);

        return cal;
    }

    public String getBirthDayToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return df.format(birthday);
    }

    @Override
    public String toString() {
        return "Baby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + this.getBirthDayToString() +
                ", gender=" + gender +
                ", gestationPeriod=" + gestationPeriod +
                '}';
    }
}


