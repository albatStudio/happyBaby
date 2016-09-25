package com.superkorsuk.happybaby.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.superkorsuk.happybaby.models.Baby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BabyRepository implements Repository<Baby> {
    private final BabyOpenHelper openHelper;
    private static final String dbName = "happybaby.db";
    private static final int dbVersion = 1;

    private final Mapper<Baby, ContentValues> toContentValuesMapper;
    private final Mapper<Cursor, Baby> toBabyMapper;


    public BabyRepository(Context context) {
        this.openHelper = new BabyOpenHelper(context, dbName, dbVersion);
        this.toContentValuesMapper = new BabyToContentValuesMapper();
        this.toBabyMapper = new CursorToBabyMapper();
    }

    @Override
    public long add(Baby item) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = toContentValuesMapper.map(item);

            long id = db.insert(BabyOpenHelper.tableName, null, values);
            db.setTransactionSuccessful();

            return id;
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    @Override
    public void add(Iterable<Baby> items) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        db.beginTransaction();

        try {
            for (Baby baby: items) {
                ContentValues values = toContentValuesMapper.map(baby);
                db.insert(BabyOpenHelper.tableName, null, values);
            }

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
            db.close();
        }
    }

    @Override
    public void update(Baby item) {

    }

    @Override
    public void remove(Baby item) {

    }

    public Baby find(long id) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();

        try {
            final Cursor cursor = db.query(BabyOpenHelper.tableName, new String[]{"id", "name", "birthday", "gender", "gestation_period"}, " id = ?", new String[]{String.valueOf(id)}, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                Baby baby = toBabyMapper.map(cursor);
                cursor.close();


                return baby;
            }


        } finally {
            db.close();
        }

        return null;
    }

    public List<Baby> all() {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        final List<Baby> babies = new ArrayList<>();

        try {
            final Cursor cursor = db.rawQuery("select * from " + BabyOpenHelper.tableName, new String[]{});
            for(int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);
                babies.add(toBabyMapper.map(cursor));
            }

            cursor.close();

            return babies;
        } finally {
            db.close();
        }
    }

    private class BabyToContentValuesMapper implements Mapper<Baby,ContentValues> {
        @Override
        public ContentValues map(Baby baby) {
            ContentValues cv = new ContentValues();
            cv.put("name", baby.getName());
            cv.put("birthday", baby.getBirthDayDateTime());
            cv.put("gender", baby.getGender().getValue());
            cv.put("gestation_period", baby.getGestationPeriod());

            return cv;
        }
    }


    private class BabyOpenHelper extends SQLiteOpenHelper {
        private static final String tableName = "babies";


        private BabyOpenHelper(Context context, String dbName, int dbVersion) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + tableName + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), birthday DATETIME DEFAULT CURRENT_TIMESTAMP, gender INTEGER, gestation_period INTEGER)";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS " + tableName;
            db.execSQL(sql);
            onCreate(db);
        }
    }

    private class CursorToBabyMapper implements Mapper<Cursor,Baby> {
        @Override
        public Baby map(Cursor cursor) {
            Baby baby = new Baby();

            baby.setId(Integer.parseInt(cursor.getString(0)));
            baby.setName(cursor.getString(1));

            return baby;
        }
    }
}
