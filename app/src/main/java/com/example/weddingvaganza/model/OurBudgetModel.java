package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class OurBudgetModel {

    @SerializedName("our_budget")
    private int budget;
    @SerializedName("id_user")
    private int userId;


    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
