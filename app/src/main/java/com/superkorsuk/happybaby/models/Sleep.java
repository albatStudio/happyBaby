package com.superkorsuk.happybaby.models;

import java.util.Date;

/**
 * Created by Luke on 2016-09-25.
 */

public class Sleep extends BabyDo {
    public Date startTime;
    public Date endTime;

    public int getDuration() {
        return endTime.compareTo(startTime);
    }
}
