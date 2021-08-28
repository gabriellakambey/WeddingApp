package com.example.weddingvaganza.model;

import com.example.weddingvaganza.model.RundownModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryRundownModel {

    private  int categoryId;
    private String titleCategory;
    @SerializedName("rundown")
    private List<RundownModel> rundownModelList;

    public CategoryRundownModel(String titleCategory, List<RundownModel> rundownModelList) {
        this.titleCategory = titleCategory;
        this.rundownModelList = rundownModelList;
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

    public List<RundownModel> getRundownModelList() {
        return rundownModelList;
    }

    public void setRundownModelList(List<RundownModel> rundownModelList) {
        this.rundownModelList = rundownModelList;
    }
}
