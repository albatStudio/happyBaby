package com.superkorsuk.happybaby.util;

import java.util.Calendar;

/**
 * Created by 1001078 on 2016. 9. 20..
 */
public class DateAndTime {

    // targetDate 가 sourceDate 로 부터 몇일 지났음. (- 이면 몇일 남았음.)
    public static long compareDate(Calendar sourceDate, Calendar targetDate) {
        long compareSec = (targetDate.getTimeInMillis() - sourceDate.getTimeInMillis())/1000;
        return compareSec / (24*60*60);
    }

    // targetDate 가 현재로 부터 몇일 지났음. (- 이면 몇일 남았음.)
    public static long compareDateFromNow(Calendar targetDate) {
        long compareSec = (Calendar.getInstance().getTimeInMillis() - targetDate.getTimeInMillis())/1000;
        return compareSec / (24*60*60);
    }

    // targetDate 가 sourceDate 로 부터 몇개월 지났음.
    public static long compareMonth(Calendar sourceDate, Calendar targetDate) {
        long compareYear = targetDate.get(Calendar.YEAR) - sourceDate.get(Calendar.YEAR);
        long compareMonth = targetDate.get(Calendar.MONTH) - sourceDate.get(Calendar.MONTH);
        return (compareYear * 12) + compareMonth;
    }

    // targetDate 가 현재로 부터 몇개월 지났음...
    public static long compareMonthFromNow(Calendar targetDate) {
        long compareYear = Calendar.getInstance().get(Calendar.YEAR) - targetDate.get(Calendar.YEAR) ;
        long compareMonth = Calendar.getInstance().get(Calendar.MONTH) - targetDate.get(Calendar.MONTH);
        return (compareYear * 12) + compareMonth;
    }

    // sourceData 로부터 daysToAdd 일 후는 몇일?
    public static Calendar dateAdd(Calendar sourceDate, int daysToAdd) {
        sourceDate.add(Calendar.DATE, daysToAdd);
        return sourceDate;
    }

    // 현재부터 daysToAdd 일 후는 몇일?
    public static Calendar dateAdd(int daysToAdd) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, daysToAdd);
        return now;
    }
}
