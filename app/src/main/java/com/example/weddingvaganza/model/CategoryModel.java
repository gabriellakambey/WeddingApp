package com.example.weddingvaganza.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int categoryId;
    private String categoryTitle;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CategoryModel(int categoryId , String categoryTitle) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    public String toString(){
        return categoryTitle;
    }


}
