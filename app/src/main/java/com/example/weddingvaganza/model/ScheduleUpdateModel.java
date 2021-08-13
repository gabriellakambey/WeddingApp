package com.example.weddingvaganza.model;

public class ScheduleUpdateModel {

//    {
//        "scheduleId": 1,
//            "date": "12/10/2021",
//            "title": "coba coba",
//            "categoryId": 1,
//            "note": "ok",
//            "userId": 1,
//            "status": "unchecked",
//            "month": 12,
//            "year": 2021
//    }

    private int scheduleId;
    private String date;
    private String title;
    private int categoryId;
    private String note;
    private int userId;
    private String status;
    private int month;
    private int year;

    public ScheduleUpdateModel(int scheduleId, String date, String title, int categoryId, String note, int userId, String status, int month, int year) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.title = title;
        this.categoryId = categoryId;
        this.note = note;
        this.userId = userId;
        this.status = status;
        this.month = month;
        this.year = year;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
