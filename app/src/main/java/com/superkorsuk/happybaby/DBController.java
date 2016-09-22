package com.superkorsuk.happybaby;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jinjunhyuk on 2016. 9. 22..
 */

public class DBController {
    private static final String DBNAME = "happybaby.sqlite";
    private static final int DBVERSION = 1;
    Context context;
    String tableName;
    DBControllerHelper helper;
    protected SQLiteDatabase db;

    public DBController(Context context, String tableName) {
        this.context = context;
        this.tableName = tableName;

        helper = new DBControllerHelper(context, DBNAME, DBVERSION);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e("DB", "Cannot get Database.");
        }
    }

    public long insert(Baby babyInfomation) {
        ContentValues values = new ContentValues();
        values.put("name", babyInfomation.getName());
        values.put("birthday_year", babyInfomation.getYear());
        values.put("birthday_month", babyInfomation.getMonth());
        values.put("birthday_day", babyInfomation.getDay());
        values.put("sex", babyInfomation.getDay());
        values.put("pregnant", babyInfomation.getPregnant());
        values.put("weight", babyInfomation.getWeight());

        return db.insert(tableName, null, values);
    }

    // TODO : Milk data insert method
//    public long insert(MilkData milkData ) {
//        ....
//    }


    ///////////// old.
    public long insert(String name, int year, int month, int day, int sex, int pregnant, int weight) {

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("birthday_year", year);
        values.put("birthday_month", month);
        values.put("birthday_day", day);
        values.put("sex", sex);
        values.put("pregnant", pregnant);
        values.put("weight", weight);

        return db.insert(tableName, null, values);
    }

    private class DBControllerHelper extends SQLiteOpenHelper {

        public DBControllerHelper(Context context, String dbName, int dbVersion) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = "CREATE TABLE " + tableName + " (name VARCHAR(200) PRIMARY KEY, birthday_year INT, birthday_month INT, birthday_day INT, sex INT, pregnant INT, weight INT);";
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql = "DROP TABLE IF EXISTS " + tableName;
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }

        private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
            if(oldVersion < 1)
                onCreate(db);
            else
                onUpgrade(db, oldVersion, newVersion);
        }
    }


}
