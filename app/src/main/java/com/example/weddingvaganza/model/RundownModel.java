package com.example.weddingvaganza.model;

public class RundownModel {
    private int rundownId;
    private String time;
    private String rundownTitle;
    private int categoryId;
    private String rundownNote;
    private String namaPenanggungJawab;

    public int getRundownId() {
        return rundownId;
    }

    public void setRundownId(int rundownId) {
        this.rundownId = rundownId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRundownTitle() {
        return rundownTitle;
    }

    public void setRundownTitle(String rundownTitle) {
        this.rundownTitle = rundownTitle;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRundownNote() {
        return rundownNote;
    }

    public void setRundownNote(String rundownNote) {
        this.rundownNote = rundownNote;
    }

    public String getNamaPenanggungJawab() {
        return namaPenanggungJawab;
    }

    public void setNamaPenanggungJawab(String namaPenanggungJawab) {
        this.namaPenanggungJawab = namaPenanggungJawab;
    }
}
