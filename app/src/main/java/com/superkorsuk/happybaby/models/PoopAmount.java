package com.superkorsuk.happybaby.models;

/**
 * Created by Luke on 2016-09-25.
 */

public enum PoopAmount {
    SMALL(10), MEDIUM(20), LARGE(30);
    private int value;

    PoopAmount(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
