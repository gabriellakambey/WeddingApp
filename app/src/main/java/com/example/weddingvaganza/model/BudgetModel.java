package com.example.weddingvaganza.model;

public class BudgetModel {
    private String titleBudget;
    private String totalBudget;

    public BudgetModel(String titleBudget,String totalBudget){
        this.titleBudget = titleBudget;
        this.totalBudget = totalBudget;
    }

    public String getTitleBudget() {
        return titleBudget;
    }

    public void setTitleBudget(String titleBudget) {
        this.titleBudget = titleBudget;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }
}
