package com.example.weddingvaganza.model;

public class BudgetModel {
    private String titleBudget;
    private int totalBudget;

    public BudgetModel(String titleBudget,int totalBudget){
        this.titleBudget = titleBudget;
        this.totalBudget = totalBudget;
    }

    public String getTitleBudget() {
        return titleBudget;
    }

    public void setTitleBudget(String titleBudget) {
        this.titleBudget = titleBudget;
    }

    public int getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(int totalBudget) {
        this.totalBudget = totalBudget;
    }
}
