package com.example.weddingvaganza.model;

public class JsonMember{
	private String date;
	private String note;
	private int month;
	private int year;
	private FkCategoryId fkCategoryId;
	private FkUserId fkUserId;
	private String title;
	private int scheduleId;
	private String status;

	public String getDate(){
		return date;
	}

	public String getNote(){
		return note;
	}

	public int getMonth(){
		return month;
	}

	public int getYear(){
		return year;
	}

	public FkCategoryId getFkCategoryId(){
		return fkCategoryId;
	}

	public FkUserId getFkUserId(){
		return fkUserId;
	}

	public String getTitle(){
		return title;
	}

	public int getScheduleId(){
		return scheduleId;
	}

	public String getStatus(){
		return status;
	}
}
