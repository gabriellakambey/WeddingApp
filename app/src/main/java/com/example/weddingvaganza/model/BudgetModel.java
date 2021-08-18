package com.example.weddingvaganza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BudgetModel implements Parcelable {

    private int budgetId;
    @SerializedName("titleBudget")
    private String titleBudget;
    @SerializedName("totalBudget")
    private int costBudget;
    private String note;
    private String status;
    private int paid;
    private int userId;

    public BudgetModel(int budgetId,String titleBudget, int costBudget, String note, String status, int paid, int userId){
        this.budgetId = budgetId;
        this.titleBudget = titleBudget;
        this.costBudget = costBudget;
        this.note = note;
        this.status = status;
        this.paid = paid;
        this.userId = userId;
    }

    protected BudgetModel(Parcel in) {
        budgetId = in.readInt();
        titleBudget = in.readString();
        costBudget = in.readInt();
        note = in.readString();
        status = in.readString();
        paid = in.readInt();
        userId = in.readInt();
    }

    public static final Creator<BudgetModel> CREATOR = new Creator<BudgetModel>() {
        @Override
        public BudgetModel createFromParcel(Parcel in) {
            return new BudgetModel(in);
        }

        @Override
        public BudgetModel[] newArray(int size) {
            return new BudgetModel[size];
        }
    };

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(budgetId);
        dest.writeString(titleBudget);
        dest.writeInt(costBudget);
        dest.writeString(note);
        dest.writeString(status);
        dest.writeInt(paid);
        dest.writeInt(userId);
    }
}
