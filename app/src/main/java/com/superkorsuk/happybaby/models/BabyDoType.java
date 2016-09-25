package com.superkorsuk.happybaby.models;

/**
 * Created by Luke on 2016-09-25.
 */
public enum BabyDoType {
    FORMULA(1), BREAST_MILK(2), POOP(3), SLEEP(4);
    private int value;

    BabyDoType(int value) {
        this.value = value;
    }
}
