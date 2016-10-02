package com.superkorsuk.happybaby;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RepositoryTest {
    private MainActivity activity;
    private static final int BABY_ID = 1;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        this.activity = mActivityRule.getActivity();
    }

    @Test
    public void selectAllByBabyIdShouldWork() {
        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        List<BabyDo> babyDoList = repo.all(BABY_ID);
        List<BabyDo> babyDoList2 = repo.all(2);

        Log.d("UNIT_TEST", "size is " + babyDoList.size() + ", " + babyDoList2.size());
        assertTrue(babyDoList.size() > babyDoList2.size());
    }

    @Test
    public void selectAllPoopByBabyId() {
        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        Map<String, Object> where = new HashMap<>();
        where.put("babyId", BABY_ID);
        where.put("babyDoType", BabyDoType.POOP);

        List<BabyDo> babyDoList = repo.queryFor(where);


        Log.d("UNIT_TEST", "poops size : " + babyDoList.size());
        assertTrue(babyDoList.size() > 0);
    }

    @Test
    public void selectTodayDoList() {
        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        List<BabyDo> babyDoList = repo.todayBabyDoList(BABY_ID);


        Log.d("UNIT_TEST", "today count : " + babyDoList.size());
        assertEquals(14, babyDoList.size());
    }

    @Test
    public void getTodayFeedingAmount() {

        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        int amount = repo.getFeedingAmountToday(BABY_ID);
        int amountYesterday = repo.getFeedingAmountYesterday(BABY_ID);

        Log.d("UNIT_TEST", "amount (yesterday, today) is : (" + amountYesterday + ", " + amount + ")");
        assertEquals(485, amount);
        assertEquals(365, amountYesterday);
    }

    @Test
    public void getTodayFeedingDuration() {
        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        int duration = repo.getFeedingDurationToday(BABY_ID);

        Log.d("UNIT_TEST", "duration is : " + duration);
        assertEquals(35, duration);
    }


}
