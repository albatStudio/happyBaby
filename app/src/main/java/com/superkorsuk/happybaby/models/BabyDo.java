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
    public static final String BABY_ID_FIELD_NAME = "babyId";
    public static final String ISSUE_DATE_FIELD_NAME = "issueDate";
    public static final String BABY_DO_TYPE_FIELD_NAME = "babyDoType";

    // common
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(indexName = "baby_do_idx1", canBeNull = false)
    private int babyId;

    @DatabaseField(dataType = DataType.ENUM_INTEGER, indexName = "baby_do_idx1")
    private BabyDoType babyDoType;

    @DatabaseField(dataType = DataType.DATE, indexName = "baby_do_idx1")
    private Date issueDate = new Date();

    @DatabaseField(dataType = DataType.LONG_STRING)
    private String note;


    // 수유 - 용량 (분유, 유축한 모유)
    @DatabaseField
    private int amount = 0;

    // 수유 - 시간. 분단위 (모유)
    @DatabaseField
    private int breastfeedingLeft = 0;

    @DatabaseField
    private int breastfeedingRight = 0;

    // for poop (대변)
    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private PoopAmount amountPoop;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private PoopColor color;

    // for duration (수면)
    @DatabaseField(dataType = DataType.DATE)
    private Date startTime;

    @DatabaseField(dataType = DataType.DATE)
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

    public int getBreastfeedingLeft() {
        return breastfeedingLeft;
    }

    public void setBreastfeedingLeft(int breastfeedingLeft) {
        this.breastfeedingLeft = breastfeedingLeft;
    }

    public int getBreastfeedingRight() {
        return breastfeedingRight;
    }

    public void setBreastfeedingRight(int breastfeedingRight) {
        this.breastfeedingRight = breastfeedingRight;
    }

    public Calendar getDateCalendar() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(issueDate);

        return cal;
    }

    public String getIssueDateToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return df.format(issueDate);
    }

    public int getSleepDuration() {
        return endTime.compareTo(startTime);
    }


}
