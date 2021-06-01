package com.example.weddingvaganza.model;

public class CategoryModel {
    private int id_category;
    private String category_title;
    private String schedule_title;
    private String date;
    private String note_category;

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getSchedule_title() {
        return schedule_title;
    }

    public void setSchedule_title(String schedule_title) {
        this.schedule_title = schedule_title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote_category() {
        return note_category;
    }

    public void setNote_category(String note_category) {
        this.note_category = note_category;
    }
}
