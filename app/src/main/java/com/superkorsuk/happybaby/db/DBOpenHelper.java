package com.superkorsuk.happybaby.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_BABIES = "babies";
    public static final String TABLE_BABY_DO = "baby_do";

    private static final String dbName = "happybaby.db";
    private static final int dbVersion = 2;


    public DBOpenHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_BABIES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), birthday DATETIME DEFAULT CURRENT_TIMESTAMP, gender INTEGER, gestation_period INTEGER)";
        db.execSQL(sql);

        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE ")
                .append(TABLE_BABY_DO)
                .append(" (id INTEGER PRIMARY KEY AUTOINCREMENT")
                .append(", issue_date DATETIME DEFAULT CURRENT_TIMESTAMP")
                .append(", do_type INTEGER ")
                .append(", note TEXT")
                .append(", amount INTEGER")
                .append(", start_date DATETIME DEFAULT CURRENT_TIMESTAMP")
                .append(", end_date DATETIME DEFAULT CURRENT_TIMESTAMP")
                .append(", breast_left INTEGER")
                .append(", breast_right INTEGER")
                .append(", poop_color INTEGER")
                .append(" )");

        db.execSQL(sb.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlBabies = "DROP TABLE IF EXISTS " + TABLE_BABIES;
        db.execSQL(sqlBabies);


        String sqlBabyDo = "DROP TABLE IF EXISTS " + TABLE_BABY_DO;
        db.execSQL(sqlBabyDo);

        onCreate(db);
    }
}
