package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleModel {
    private int scheduleId;
    @SerializedName("date")
    private String dateSchedule;
    @SerializedName("title")
    private String titleSchedule;
    @SerializedName("categoryId")
    private int idCategory;
    @SerializedName("note")
    private String noteSchedule;
    private int userId;
    private String status;
    private int month;
    private int year;

    public ScheduleModel(String status) {
        this.status = status;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(String dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public String getTitleSchedule() {
        return titleSchedule;
    }

    public void setTitleSchedule(String titleSchedule) {
        this.titleSchedule = titleSchedule;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNoteSchedule() {
        return noteSchedule;
    }

    public void setNoteSchedule(String noteSchedule) {
        this.noteSchedule = noteSchedule;
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
