package com.superkorsuk.happybaby.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoFactory;
import com.superkorsuk.happybaby.models.Gender;
import com.superkorsuk.happybaby.models.PoopAmount;
import com.superkorsuk.happybaby.models.PoopColor;
import com.superkorsuk.happybaby.util.DateAndTime;

import java.sql.SQLException;

public class DBOpenHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "happybaby.db";
    private static final int DB_VERSION = 4;

    private Dao<Baby, Integer> babyDao = null;
    private Dao<BabyDo, Integer> babyDoDao = null;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, Baby.class);
            TableUtils.createTable(cs, BabyDo.class);

            insertBabySamples();
            insertBabyDoSamples();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, Baby.class, true);
            TableUtils.dropTable(cs, BabyDo.class, true);

            onCreate(db, cs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Baby, Integer> getBabyDao() throws SQLException {
        if (babyDao == null) {
            babyDao = getDao(Baby.class);
        }
        return babyDao;
    }

    public Dao<BabyDo, Integer> getBabyDoDao() throws SQLException {
        if (babyDoDao == null) {
            babyDoDao = getDao(BabyDo.class);
        }
        return babyDoDao;
    }

    private void insertBabySamples() {
        Baby baby = new Baby();
        baby.setId(1);
        baby.setName("황율");
        baby.setGender(Gender.FEMALE);
        baby.setGestationPeriod(47, 3);
        baby.setBirthday(DateAndTime.getDate(2016, 5, 29, 1, 1));
        baby.setBirthWeight(3.25);


        try {
            getBabyDao().create(baby);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertBabyDoSamples() {
        try {
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 8, 30).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 11, 0).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 13, 50).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 14, 30).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 15, 20).getTime(), 15, "" ));
            getBabyDoDao().create(BabyDoFactory.getPoop(1, DateAndTime.getDate(2016, 6, 19, 15, 40).getTime(), PoopAmount.LARGE, PoopColor.YELLOW, "" ));

            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 16, 35).getTime(), 15, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 18, 0).getTime(), 15, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 19, 19, 10).getTime(), 20, "" ));
            getBabyDoDao().create(BabyDoFactory.getFormula(1, DateAndTime.getDate(2016, 6, 19, 21, 40).getTime(), 60, "" ));

            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 0, 40).getTime(), 15, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 1, 20).getTime(), 30, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 4, 30).getTime(), 10, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 5, 30).getTime(), 20, "" ));
            getBabyDoDao().create(BabyDoFactory.getFormula(1, DateAndTime.getDate(2016, 6, 20, 7, 0).getTime(), 55, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 10, 0).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastfeeding(1, DateAndTime.getDate(2016, 6, 20, 10, 0).getTime(), 5, 0, "" ));

            getBabyDoDao().create(BabyDoFactory.getBreastfeeding(1, DateAndTime.getDate(2016, 6, 20, 13, 0).getTime(), 15, 0, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 13, 0).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getFormula(1, DateAndTime.getDate(2016, 6, 20, 16, 15).getTime(), 55, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastfeeding(1, DateAndTime.getDate(2016, 6, 20, 19, 20).getTime(), 15, 0, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 19, 20).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getBreastMilk(1, DateAndTime.getDate(2016, 6, 20, 22, 20).getTime(), 60, "" ));
            getBabyDoDao().create(BabyDoFactory.getFormula(1, DateAndTime.getDate(2016, 6, 20, 23, 40).getTime(), 60, "" ));



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
