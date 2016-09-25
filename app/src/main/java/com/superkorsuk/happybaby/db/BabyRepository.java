package com.superkorsuk.happybaby.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.superkorsuk.happybaby.models.Baby;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BabyRepository implements Repository<Baby> {
    private final DBOpenHelper openHelper;

    public BabyRepository(Context context) {
        this.openHelper = new DBOpenHelper(context);
    }

    @Override
    public int create(Baby item) {

        int result = 0;
        try {
            result = openHelper.getBabyDao().create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(Baby item) {
        try {
            openHelper.getBabyDao().update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(Baby item) {
        try {
             openHelper.getBabyDao().delete(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Baby find(int id) {

        Baby baby = null;
        try {
            baby = openHelper.getBabyDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baby;
    }

    public List<Baby> all() {
        List<Baby> babies = new ArrayList<>();

        Dao<Baby, Integer> babyDao = null;
        try {
            babyDao = openHelper.getBabyDao();
            babies = babyDao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return babies;
    }

}
