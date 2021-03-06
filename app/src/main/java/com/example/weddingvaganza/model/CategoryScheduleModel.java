package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  CategoryScheduleModel {
    private int categoryId;
    private String titleCategory;
    @SerializedName("scheduleModel")
    private List<ScheduleModel> scheduleModelList;

    public CategoryScheduleModel(String titleCategory, List<ScheduleModel> scheduleModels) {
        this.titleCategory = titleCategory;
        this.scheduleModelList = scheduleModels;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }

    public List<ScheduleModel> getScheduleModels() {
        return scheduleModelList;
    }

    public void setScheduleModels(List<ScheduleModel> scheduleModels) {
        this.scheduleModelList = scheduleModels;
    }
}
