package com.superkorsuk.happybaby.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.superkorsuk.happybaby.models.BabyDo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BabyDoRepository implements Repository<BabyDo> {

    private final DBOpenHelper openHelper;

    public BabyDoRepository(Context context) {
        this.openHelper = new DBOpenHelper(context);
    }

    @Override
    public int create(BabyDo item) {
        int result = 0;
        try {
            result = openHelper.getBabyDoDao().create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<BabyDo> all() {
        List<BabyDo> babies = new ArrayList<>();

        Dao<BabyDo, Integer> babyDoDao = null;
        try {
            babyDoDao = openHelper.getBabyDoDao();
            babies = babyDoDao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return babies;
    }

    @Override
    public void update(BabyDo item) {
        try {
            openHelper.getBabyDoDao().update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(BabyDo item) {
        try {
            openHelper.getBabyDoDao().delete(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
