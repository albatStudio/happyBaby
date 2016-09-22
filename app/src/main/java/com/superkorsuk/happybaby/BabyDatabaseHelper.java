package com.superkorsuk.happybaby;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by super on 2016-09-20.
 */
public class BabyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE baby values(name VARCHAR(200) PRIMARY KEY, birthdayYear INT, birthdayMonth INT, birthdayDay INT, sex INT, pregnant INT, weight INT, photo BLOB";
    private static final String DROP_TABLE = "DROP TABLE baby";

    BabyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 1) {
            db.execSQL(CREATE_TABLE);
        } else {
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        }
    }
}
