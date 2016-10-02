package com.superkorsuk.happybaby.models;

import java.util.Date;

public class BabyDoFactory {

    /**
     * 분유 수유할 때
     *
     * @param babyId
     * @param issueDate
     * @param amount 단위 : ml
     * @param note
     * @return
     */
    public static BabyDo getFormula(int babyId, Date issueDate, int amount, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.FORMULA);
        bd.setBabyId(babyId);
        bd.setIssueDate(issueDate);
        bd.setNote(note);

        bd.setAmount(amount);

        return bd;
    }

    /**
     * 유축한 모유를 수유할 때
     *
     * @param babyId
     * @param issueDate
     * @param amount
     * @param note
     * @return
     */
    public static BabyDo getBreastMilk(int babyId, Date issueDate, int amount, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.BREAST_MILK);
        bd.setBabyId(babyId);
        bd.setIssueDate(issueDate);
        bd.setNote(note);

        bd.setAmount(amount);

        return bd;
    }

    /**
     * 직접 수유할 때.
     *
     * @param babyId
     * @param issueDate
     * @param left 단위: 분. 좌우 구분안할 시 left만 사용.
     * @param right 단위: 분
     * @param note
     * @return
     */
    public static BabyDo getBreastfeeding(int babyId, Date issueDate, int left, int right, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.BREAST_MILK);
        bd.setBabyId(babyId);
        bd.setIssueDate(issueDate);
        bd.setNote(note);

        bd.setBreastfeedingLeft(left);
        bd.setBreastfeedingRight(right);

        return bd;
    }

    /**
     * 대변
     *
     * @param babyId
     * @param issueDate
     * @param amountPoop
     * @param color
     * @param note
     * @return
     */
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

    /**
     * 수면 시간
     *
     * @param babyId
     * @param startTime
     * @param endTime
     * @param note
     * @return
     */
    public static BabyDo getSleep(int babyId, Date startTime, Date endTime, String note) {
        BabyDo bd = new BabyDo();
        bd.setBabyDoType(BabyDoType.SLEEP);
        bd.setBabyId(babyId);
        bd.setIssueDate(startTime);
        bd.setNote(note);

        bd.setStartTime(startTime);
        bd.setEndTime(endTime);

        return bd;
    }
}
