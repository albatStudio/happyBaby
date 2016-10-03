package com.superkorsuk.happybaby;


import com.superkorsuk.happybaby.util.DateAndTime;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getDDay_isCorrect() throws Exception {
        Calendar today = Calendar.getInstance(Locale.getDefault());
        today.set(Calendar.HOUR_OF_DAY, 12);
        Calendar yesterday = Calendar.getInstance(Locale.getDefault());

        yesterday.add(Calendar.DATE, -1);
        yesterday.set(Calendar.HOUR_OF_DAY, 13);

        Calendar tomorrow = Calendar.getInstance(Locale.getDefault());
        tomorrow.add(Calendar.DATE, 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, 13);

        assertEquals(2, DateAndTime.getDDay(yesterday));

        yesterday.set(Calendar.HOUR_OF_DAY, 11);
        assertEquals(2, DateAndTime.getDDay(yesterday));

        assertEquals(-1, DateAndTime.getDDay(tomorrow));
        tomorrow.set(Calendar.HOUR_OF_DAY, 11);
        assertEquals(-1, DateAndTime.getDDay(tomorrow));






    }

}

