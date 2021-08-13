package com.example.weddingvaganza.model.responseModel;

import com.example.weddingvaganza.model.BudgetModel;
import com.google.gson.annotations.SerializedName;

public class AddBudgetResponse {

    private String status;
    @SerializedName("BudgetList")
    private BudgetModel budgetModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BudgetModel getBudgetModel() {
        return budgetModel;
    }

    public void setBudgetModel(BudgetModel budgetModel) {
        this.budgetModel = budgetModel;
    }
}
