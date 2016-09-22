package com.superkorsuk.happybaby;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by super on 2016-09-21.
 */

public class DBBabyInformationHelper extends SQLiteOpenHelper {

    private String tableName = "babyInformation";

    public DBBabyInformationHelper(DBBabyInformation dbBabyInformation, String dbName, Context context, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + tableName + " (name VARCHAR(200) PRIMARY KEY, birthdayYear INT, birthdayMonth INT, birthdayDay INT, sex INT, pregnant INT, weight INT, photo BLOB);";
        db.execSQL(sql);
    }

    @Override
    public void  onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 1)
            onCreate(db);
        else
            onUpgrade(db, oldVersion, newVersion);
    }

    public String getTableName() {
        return tableName;
    }
}