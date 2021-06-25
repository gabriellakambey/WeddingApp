package com.example.weddingvaganza.model;

import java.util.List;

public class CategoryScheduleModel {
    private int categoryId;
    private String categoryTitle;
    private List<ScheduleModel> scheduleModels;

    public CategoryScheduleModel(String categoryTitle, List<ScheduleModel> scheduleModels) {
        this.categoryTitle = categoryTitle;
        this.scheduleModels = scheduleModels;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<ScheduleModel> getScheduleModels() {
        return scheduleModels;
    }

    public void setScheduleModels(List<ScheduleModel> scheduleModels) {
        this.scheduleModels = scheduleModels;
    }
}
