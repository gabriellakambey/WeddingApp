package com.example.weddingvaganza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BudgetModel implements Parcelable {

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

    protected BudgetModel(Parcel in) {
        titleBudget = in.readString();
        costBudget = in.readInt();
        note = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleBudget);
        dest.writeInt(costBudget);
        dest.writeString(note);
        dest.writeInt(userId);
    }
}
