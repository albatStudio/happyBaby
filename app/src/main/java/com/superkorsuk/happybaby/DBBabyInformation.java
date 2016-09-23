package com.superkorsuk.happybaby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by super on 2016-09-21.
 */
public class DBBabyInformation {
    private static final String DBNAME = "habbybaby.sqlite";
    private static final int DBVERSION = 1;
    private DBBabyInformationHelper helper;
    protected SQLiteDatabase db;
    private String TABLENAME;
    String logTag = "SQLite";

    public DBBabyInformation(Context context) {
        helper = new DBBabyInformationHelper(context, DBNAME, DBVERSION);

        try {
            db = helper.getWritableDatabase();
            TABLENAME = helper.getTableName();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(logTag, "Cannot get Database.");
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

        return db.insert(TABLENAME, null, values);
    }

//    name VARCHAR(200) PRIMARY KEY, birthdayYear INT, birthdayMonth INT, birthdayDay INT, sex INT, pregnant INT, weight INT, photo BLOB
    public void insert(String name, int year, int month, int day, int sex, int pregnant, int weight) {

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("birthday_year", year);
        values.put("birthday_month", month);
        values.put("birthday_day", day);
        values.put("sex", sex);
        values.put("pregnant", pregnant);
        values.put("weight", weight);

        db.insert(TABLENAME, null, values);

/*        String sql = "INSERT OR REPLACE INTO " + tableName + "(name, birthday_year, birthday_month, birthday_day, sex, pregnant, weight) "
                     + "values ('" + name + "', " + year + ", " + month + ", " + day + ", " + sex + ", " + pregnant + ", " + weight + ");"  ;

        db.execSQL(sql);*/

        Log.d(logTag, "insert succeed.");
    }

    public void update() {

    }

    public void delete(String name) {
        db.delete(TABLENAME, "name=?", new String[]{name});
        Log.i(logTag, name + " was deleted successfully.");
    }

    public void select(String name) {
        Baby babyInfomation = new Baby();

        Cursor c = db.query(TABLENAME, new String[] {name}, "name=?", null, null, null, null);



    }

    public void close() {
        if(db != null) {
            db.close();
        }
    }


}
