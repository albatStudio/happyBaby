package com.superkorsuk.happybaby.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.superkorsuk.happybaby.models.Baby;

import java.sql.SQLException;

public class DBOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String TABLE_BABIES = "babies";
    public static final String TABLE_BABY_DO = "baby_do";

    private static final String DATABASE_NAME = "happybaby.db";
    private static final int DB_VERSION = 2;

    private Dao<Baby, Integer> babyDao = null;


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {

        try {
            TableUtils.createTable(cs, Baby.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, Baby.class, true);
            onCreate(db, cs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sqlBabyDo = "DROP TABLE IF EXISTS " + TABLE_BABY_DO;
        db.execSQL(sqlBabyDo);

        onCreate(db);
    }

    public Dao<Baby, Integer> getBabyDao() throws SQLException {
        if (babyDao == null) {
            babyDao = getDao(Baby.class);
        }
        return babyDao;
    }
}
