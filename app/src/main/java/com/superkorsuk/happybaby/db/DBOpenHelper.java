package com.superkorsuk.happybaby.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.BabyDo;

import java.sql.SQLException;

public class DBOpenHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "happybaby.db";
    private static final int DB_VERSION = 2;

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
}
