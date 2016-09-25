package com.superkorsuk.happybaby.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;
import com.superkorsuk.happybaby.models.BreastMilk;
import com.superkorsuk.happybaby.models.Formula;
import com.superkorsuk.happybaby.models.Poop;
import com.superkorsuk.happybaby.models.Sleep;

import java.util.ArrayList;
import java.util.List;

public class BabyDoRepository implements Repository<BabyDo> {

    private final DBOpenHelper openHelper;
    private static final String dbName = "happybaby.db";
    private static final int dbVersion = 1;

    private final Mapper<BabyDo, ContentValues> toContentValuesMapper;
    private final Mapper<Cursor, BabyDo> toBabyDoMapper;

    public BabyDoRepository(Context context) {
        this.openHelper = new DBOpenHelper(context);
        this.toContentValuesMapper = new BabyDoToContentValuesMapper();
        this.toBabyDoMapper = new CursorToBabyDoMapper();

    }

    @Override
    public long add(BabyDo item) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();

        try {
            ContentValues values = toContentValuesMapper.map(item);

            long id = db.insert(DBOpenHelper.TABLE_BABY_DO, null, values);

            return id;
        } finally {
            db.close();
        }
    }

    @Override
    public void add(Iterable<BabyDo> items) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();

        try {
            for (BabyDo babyDo : items) {
                ContentValues values = toContentValuesMapper.map(babyDo);
                db.insert(DBOpenHelper.TABLE_BABY_DO, null, values);
            }
        } finally {
            db.close();
        }
    }

    public List<BabyDo> all() {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        final List<BabyDo> babyDoList = new ArrayList<>();

        try {
            final Cursor cursor = db.rawQuery("select * from " + DBOpenHelper.TABLE_BABY_DO, new String[]{});
            for(int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);
                babyDoList.add(toBabyDoMapper.map(cursor));
            }
            cursor.close();

            return babyDoList;
        } finally {
            db.close();
        }
    }

    @Override
    public void update(BabyDo item) {

    }

    @Override
    public void remove(BabyDo item) {

    }


    private class BabyDoToContentValuesMapper implements Mapper<BabyDo, ContentValues> {
        @Override
        public ContentValues map(BabyDo babyDo) {
            ContentValues cv = new ContentValues();
            cv.put("issue_date", babyDo.getIssueDateToDateTime());
            cv.put("do_type", babyDo.getBabyDoType().getValue());
            cv.put("note", babyDo.getNote());

            if (BabyDoType.FORMULA == babyDo.getBabyDoType()) {
                Formula bd = (Formula) babyDo;
                cv.put("amount", bd.getAmount());

            } else if (BabyDoType.BREAST_MILK == babyDo.getBabyDoType()) {
                BreastMilk bd = (BreastMilk) babyDo;
                cv.put("breast_left", bd.getLeftAmount());
                cv.put("breast_right", bd.getRightAmount());

            } else if (BabyDoType.POOP == babyDo.getBabyDoType()) {
                Poop bd = (Poop) babyDo;
                cv.put("amount", bd.getAmount().getValue());
                cv.put("poop_color", bd.getColor().getValue());

            } else if (BabyDoType.SLEEP == babyDo.getBabyDoType()) {
                Sleep bd = (Sleep) babyDo;
                cv.put("start_date", bd.getStartTimeToDateTime());
                cv.put("end_date", bd.getEndTimeToDateTime());
            }

            return cv;
        }
    }

    private class CursorToBabyDoMapper implements Mapper<Cursor, BabyDo> {

        @Override
        public BabyDo map(Cursor cursor) {
            BabyDo babyDo = new BabyDo();

            babyDo.setId(Integer.parseInt(cursor.getString(0)));
            // issue_date
            // do_type
            babyDo.setNote(cursor.getString(2));

            // TODO more members

            return babyDo;
        }
    }
}
