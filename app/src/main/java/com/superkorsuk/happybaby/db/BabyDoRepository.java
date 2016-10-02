package com.superkorsuk.happybaby.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;
import com.superkorsuk.happybaby.util.DateAndTime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BabyDoRepository implements Repository<BabyDo> {

    private final DBOpenHelper openHelper;

    public BabyDoRepository(Context context) {
        this.openHelper = new DBOpenHelper(context);
    }

    @Override
    public int create(BabyDo item) {
        int result = 0;
        try {
            result = openHelper.getBabyDoDao().create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<BabyDo> all() {
        List<BabyDo> babies = new ArrayList<>();

        Dao<BabyDo, Integer> babyDoDao = null;
        try {
            babyDoDao = openHelper.getBabyDoDao();
            babies = babyDoDao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return babies;
    }

    @Override
    public void update(BabyDo item) {
        try {
            openHelper.getBabyDoDao().update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(BabyDo item) {
        try {
            openHelper.getBabyDoDao().delete(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BabyDo> all(int babyId) {
        List<BabyDo> babies = new ArrayList<>();

        try {
            babies = openHelper.getBabyDoDao().queryForEq(BabyDo.BABY_ID_FIELD_NAME, babyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return babies;
    }

    public List<BabyDo> queryFor(Map<String, Object> fieldValues) {
        List<BabyDo> babies = new ArrayList<>();

        try {
            babies = openHelper.getBabyDoDao().queryForFieldValues(fieldValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return babies;
    }

    /**
     * 아래 참고.
     * http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_3.html#QueryBuilder-Basics
     * @param babyId
     * @return
     */
    public List<BabyDo> todayBabyDoList(int babyId) {
        List<BabyDo> babies = new ArrayList<>();
        try {
            babies = openHelper.getBabyDoDao().queryBuilder()
                    .where()
                    .eq(BabyDo.BABY_ID_FIELD_NAME, babyId)
                    .and()
                    .between(BabyDo.ISSUE_DATE_FIELD_NAME, DateAndTime.getDate(2016, 6, 20, 0, 0).getTime(), new Date())
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return babies;
    }

    public int getFeedingAmountToday(int babyId) {
        return getFeedingAmountAt(babyId, 2016, 6, 20);
    }

    public int getFeedingAmountYesterday(int babyId) {
        return getFeedingAmountAt(babyId, 2016, 6, 19);
    }

    private int getFeedingAmountAt(int babyId, int year, int month, int date) {
        Date startDate = DateAndTime.getDate(year, month, date, 0, 0).getTime();
        Date nextDate = new Date(startDate.getTime() + 60 * 60 * 24 * 1000 - 1);

        String[] results = new String[]{"0"};

        try {
            results = openHelper.getBabyDoDao().queryBuilder()
                    .selectRaw("sum(amount) as totalAmount")
                    .where()
                    .eq(BabyDo.BABY_ID_FIELD_NAME, babyId)
                    .and()
                    .between(BabyDo.ISSUE_DATE_FIELD_NAME, startDate, nextDate)
                    .queryRawFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (results != null) {
            return Integer.valueOf(results[0]);
        }

        return 0;
    }

    public int getFeedingDurationToday(int babyId) {
        return getFeedingDurationAt(babyId, 2016, 6, 20);
    }

    public int getFeedingDurationYesterday(int babyId) {
        return getFeedingDurationAt(babyId, 2016, 6, 20);
    }

    private int getFeedingDurationAt(int babyId, int year, int month, int date) {
        Date startDate = DateAndTime.getDate(year, month, date, 0, 0).getTime();
        Date nextDate = new Date(startDate.getTime() + 60 * 60 * 24 * 1000 - 1);

        String[] results = new String[]{"0"};

        try {
            results = openHelper.getBabyDoDao().queryBuilder()
                    .selectRaw("sum(breastfeedingLeft + breastfeedingRight) as totalDuration")
                    .where()
                    .eq(BabyDo.BABY_ID_FIELD_NAME, babyId)
                    .and()
                    .between(BabyDo.ISSUE_DATE_FIELD_NAME, startDate, nextDate)
                    .queryRawFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (results != null) {
            return Integer.valueOf(results[0]);
        }

        return 0;
    }

    public BabyDo getLastPoop(int babyId) {

        BabyDo babyDo = null;

        try {
            babyDo = openHelper.getBabyDoDao().queryBuilder()
                    .orderBy(BabyDo.ISSUE_DATE_FIELD_NAME, false)
                    .where()
                    .eq(BabyDo.BABY_ID_FIELD_NAME, babyId)
                    .and()
                    .eq(BabyDo.BABY_DO_TYPE_FIELD_NAME, BabyDoType.POOP)
                    .queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return babyDo;
    }
}
