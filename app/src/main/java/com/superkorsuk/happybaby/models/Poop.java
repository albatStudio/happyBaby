package com.superkorsuk.happybaby.models;

/**
 * Created by Luke on 2016-09-25.
 */

public class Poop extends BabyDo {

    public PoopAmount amount;
    public PoopColor color;

    public PoopAmount getAmount() {
        return amount;
    }

    public void setAmount(PoopAmount amount) {
        this.amount = amount;
    }

    public PoopColor getColor() {
        return color;
    }

    public void setColor(PoopColor color) {
        this.color = color;
    }
}
