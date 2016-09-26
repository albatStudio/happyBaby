package com.superkorsuk.happybaby.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@DatabaseTable(tableName = BabyDo.TABLE_NAME)
public class BabyDo {
    public static final String TABLE_NAME = "baby_do";

    // common
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(indexName = "baby_do_idx1")
    private int babyId;

    @DatabaseField(dataType = DataType.ENUM_INTEGER, indexName = "baby_do_idx1")
    private BabyDoType babyDoType;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss", indexName = "baby_do_idx1")
    private Date issueDate = new Date();

    @DatabaseField(dataType = DataType.LONG_STRING)
    private String note;


    // for formula (분유)
    @DatabaseField
    private int amount;

    // for breast milk (모유)
    @DatabaseField
    private int amountLeft;

    @DatabaseField
    private int amountRight;

    // for poop (대변)
    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private PoopAmount amountPoop;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private PoopColor color;

    // for sleep (수면)
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBabyId() {
        return babyId;
    }

    public void setBabyId(int babyId) {
        this.babyId = babyId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public BabyDoType getBabyDoType() {
        return babyDoType;
    }

    public void setBabyDoType(BabyDoType babyDoType) {
        this.babyDoType = babyDoType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public int getAmountRight() {
        return amountRight;
    }

    public void setAmountRight(int amountRight) {
        this.amountRight = amountRight;
    }

    public PoopAmount getAmountPoop() {
        return amountPoop;
    }

    public void setAmountPoop(PoopAmount amountPoop) {
        this.amountPoop = amountPoop;
    }

    public PoopColor getColor() {
        return color;
    }

    public void setColor(PoopColor color) {
        this.color = color;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Calendar getDateCalendar() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(issueDate);

        return cal;
    }

    public String getIssueDateToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return df.format(issueDate);
    }

    public int getSleepDuration() {
        return endTime.compareTo(startTime);
    }


}
