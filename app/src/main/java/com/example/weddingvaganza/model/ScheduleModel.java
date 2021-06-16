package com.example.weddingvaganza.model;

public class ScheduleModel {
    private int scheduleId;
    private String dateSchedule;
    private String titleSchedule;
    private int idCategory;
    private String noteSchedule;

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
}
