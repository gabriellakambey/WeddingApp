package com.example.weddingvaganza.model.schedulebyid;

public class FkCategoryId{
	private String titleCategory;
	private int userId;
	private int categoryId;

	public void setTitleCategory(String titleCategory){
		this.titleCategory = titleCategory;
	}

	public String getTitleCategory(){
		return titleCategory;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}
}
