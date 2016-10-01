package com.superkorsuk.happybaby.models;

public enum PoopColor {
    NO_INFO(0), YELLOW(1), GREEN(2), BLACK(3);
    private int value;

    PoopColor(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
