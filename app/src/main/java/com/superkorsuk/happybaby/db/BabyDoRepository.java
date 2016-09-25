package com.superkorsuk.happybaby.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.superkorsuk.happybaby.models.BabyDo;

public class BabyDoRepository implements Repository<BabyDo> {

    private final BabyDoOpenHelper openHelper;
    private static final String dbName = "happybaby.db";
    private static final int dbVersion = 1;

    private final Mapper<BabyDo, ContentValues> toContentValuesMapper;
    private final Mapper<Cursor, BabyDo> toBabyDoMapper;

    public BabyDoRepository(Context context) {
        this.openHelper = new BabyDoOpenHelper(context, dbName, dbVersion);
        this.toContentValuesMapper = new BabyDoToContentValuesMapper();
        this.toBabyDoMapper = new CursorToBabyDoMapper();

    }

    @Override
    public long add(BabyDo item) {
        return 0;
    }

    @Override
    public void add(Iterable<BabyDo> items) {

    }

    @Override
    public void update(BabyDo item) {

    }

    @Override
    public void remove(BabyDo item) {

    }

    private class BabyDoOpenHelper extends SQLiteOpenHelper {
        private static final String tableName = "baby_do";

        private BabyDoOpenHelper(Context context, String dbName, int dbVersion) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO table creation
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS " + tableName;
            db.execSQL(sql);
            onCreate(db);
        }


    }

    private class BabyDoToContentValuesMapper implements Mapper<BabyDo, ContentValues> {
        @Override
        public ContentValues map(BabyDo babyDo) {
            ContentValues cv = new ContentValues();

            return cv;
        }
    }

    private class CursorToBabyDoMapper implements Mapper<Cursor, BabyDo> {

        @Override
        public BabyDo map(Cursor cursor) {
            BabyDo babyDo = new BabyDo();

            return babyDo;
        }
    }
}
