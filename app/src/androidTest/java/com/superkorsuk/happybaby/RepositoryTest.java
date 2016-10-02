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

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RepositoryTest {
    private MainActivity activity;

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

        List<BabyDo> babyDoList = repo.all(1);
        List<BabyDo> babyDoList2 = repo.all(2);

        Log.d("test", "size is " + babyDoList.size() + ", " + babyDoList2.size());
        assertTrue(babyDoList.size() > babyDoList2.size());
    }

    @Test
    public void selectAllPoopByBabyId() {
        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        Map<String, Object> where = new HashMap<>();
        where.put("babyId", 1);
        where.put("babyDoType", BabyDoType.POOP);

        List<BabyDo> babyDoList = repo.queryFor(where);


        Log.d("test", "poops size : " + babyDoList.size());
        assertTrue(babyDoList.size() > 0);
    }

    @Test
    public void selectTodayDoList() {
        Context context = mActivityRule.getActivity().getApplicationContext();
        BabyDoRepository repo = new BabyDoRepository(context);

        List<BabyDo> babyDoList = repo.todayBabyDoList(1);


        Log.d("test", "poops size : " + babyDoList.size());
        assertTrue(babyDoList.size() == 14);
    }


}
