package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class BudgetModel {

    @SerializedName("titleBudget")
    private String titleBudget;
    @SerializedName("totalBudget")
    private int costBudget;
    private String note;
    private int userId;

    public BudgetModel(String titleBudget,int costBudget){
        this.titleBudget = titleBudget;
        this.costBudget = costBudget;
    }

    public String getTitleBudget() {
        return titleBudget;
    }

    public void setTitleBudget(String titleBudget) {
        this.titleBudget = titleBudget;
    }

    public int getCostBudget() {
        return costBudget;
    }

    public void setCostBudget(int costBudget) {
        this.costBudget = costBudget;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
