package com.superkorsuk.happybaby.models;

import java.util.Date;

public class BabyDoFactory {

    public static BabyDo getFormula(int babyId, Date issueDate, int amount, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.FORMULA);
        bd.setBabyId(babyId);
        bd.setIssueDate(issueDate);
        bd.setNote(note);

        bd.setAmount(amount);

        return bd;
    }

    public static BabyDo getBreastMilk(int babyId, Date issueDate, int amountLeft, int amountRight, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.BREAST_MILK);
        bd.setBabyId(babyId);
        bd.setIssueDate(issueDate);
        bd.setNote(note);

        bd.setAmountLeft(amountLeft);
        bd.setAmountRight(amountRight);

        return bd;
    }

    public static BabyDo getPoop(int babyId, Date issueDate, PoopAmount amountPoop, PoopColor color, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.POOP);
        bd.setBabyId(babyId);
        bd.setIssueDate(issueDate);
        bd.setNote(note);

        bd.setAmountPoop(amountPoop);
        bd.setColor(color);

        return bd;
    }

    public static BabyDo getSleep(int babyId, Date startTime, Date endTime, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.SLEEP);
        bd.setBabyId(babyId);
        bd.setIssueDate(new Date());
        bd.setNote(note);

        bd.setStartTime(startTime);
        bd.setEndTime(endTime);

        return bd;
    }
}
