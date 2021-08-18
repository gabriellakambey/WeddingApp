package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class OurBudgetModel {

    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userCouple;
    @SerializedName("date")
    private String tglPernikahan;
    private String nomorHp;
    private int ourBudget;


    public OurBudgetModel(int userId, String userName, String userEmail, String userPassword, String userCouple, String tglPernikahan, String nomorHp, int ourBudget) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCouple = userCouple;
        this.tglPernikahan = tglPernikahan;
        this.nomorHp = nomorHp;
        this.ourBudget = ourBudget;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCouple() {
        return userCouple;
    }

    public void setUserCouple(String userCouple) {
        this.userCouple = userCouple;
    }

    public String getTglPernikahan() {
        return tglPernikahan;
    }

    public void setTglPernikahan(String tglPernikahan) {
        this.tglPernikahan = tglPernikahan;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public int getOurBudget() {
        return ourBudget;
    }

    public void setOurBudget(int ourBudget) {
        this.ourBudget = ourBudget;
    }
}
