package com.superkorsuk.happybaby.models;

/**
 * Created by Luke on 2016-09-25.
 */

public class BreastMilk extends BabyDo {
    public int leftAmount;
    public int rightAmount;

    public int getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(int leftAmount) {
        this.leftAmount = leftAmount;
    }

    public int getRightAmount() {
        return rightAmount;
    }

    public void setRightAmount(int rightAmount) {
        this.rightAmount = rightAmount;
    }
}
