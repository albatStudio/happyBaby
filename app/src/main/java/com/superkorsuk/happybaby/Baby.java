package com.superkorsuk.happybaby;

/**
 * Created by jinjunhyuk on 2016. 9. 22..
 */

public class Baby {
    String name;
    int year;
    int month;
    int day;
    int sex;
    int pregnant;
    int weight;

    public Baby(String name, int year, int month, int day, int sex, int pregnant, int weight) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.sex = sex;
        this.pregnant = pregnant;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getSex() {
        return sex;
    }

    public int getPregnant() {
        return pregnant;
    }

    public int getWeight() {
        return weight;
    }
}
