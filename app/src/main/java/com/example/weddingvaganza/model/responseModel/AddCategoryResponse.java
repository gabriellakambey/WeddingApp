package com.example.weddingvaganza.model.responseModel;

import com.example.weddingvaganza.model.CategoryModel;

public class AddCategoryResponse {

    private String status;
    private CategoryModel categoryModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
}
