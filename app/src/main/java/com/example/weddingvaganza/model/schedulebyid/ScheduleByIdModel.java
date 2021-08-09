package com.example.weddingvaganza.model.schedulebyid;

public class ScheduleByIdModel{
	private String date;
	private String note;
	private int month;
	private int year;
	private FkCategoryId fkCategoryId;
	private FkUserId fkUserId;
	private String title;
	private int scheduleId;
	private String status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setMonth(int month){
		this.month = month;
	}

	public int getMonth(){
		return month;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setFkCategoryId(FkCategoryId fkCategoryId){
		this.fkCategoryId = fkCategoryId;
	}

	public FkCategoryId getFkCategoryId(){
		return fkCategoryId;
	}

	public void setFkUserId(FkUserId fkUserId){
		this.fkUserId = fkUserId;
	}

	public FkUserId getFkUserId(){
		return fkUserId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setScheduleId(int scheduleId){
		this.scheduleId = scheduleId;
	}

	public int getScheduleId(){
		return scheduleId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}
