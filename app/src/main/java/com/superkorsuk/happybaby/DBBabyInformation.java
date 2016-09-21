package com.superkorsuk.happybaby;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by super on 2016-09-21.
 */
public class DBBabyInformation {
    private static final String DBNAME = "habbybaby";
    private static final int DBVERSION = 1;
    private DBBabyInformationHelper helper = new DBBabyInformationHelper(this, DBNAME, null, DBVERSION);
    protected SQLiteDatabase db;
    String logTag = "SQLite";

    public DBBabyInformation() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(logTag, "Cannot get Database.");
        }
    }
//    name VARCHAR(200) PRIMARY KEY, birthdayYear INT, birthdayMonth INT, birthdayDay INT, sex INT, pregnant INT, weight INT, photo BLOB
    public void insert(String name, int year, int month, int day, int sex, int pregnant, int weight) {
        String tableName = helper.getTableName();
        String sql = "INSERT OR REPLACE INTO " + tableName + "(name, birthdayYear, birthdayMonth, birthdayDay, sex, pregnant, weight) "
                     + "values (" + name + ", " + year + ", " + month + ", " + day + ", " + sex + ", " + pregnant + ", " + weight + ";"  ;

        db.execSQL(sql);

        Log.d(logTag, "insert succeed.");
    }


}
