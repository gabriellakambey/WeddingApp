package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class RundownModel {
    private int rundownId;
    private String time;
    @SerializedName("titleRundown")
    private String rundownTitle;
    private int categoryId;
    @SerializedName("noteRundown")
    private String rundownNote;
    @SerializedName("pj")
    private String namaPenanggungJawab;
    private String status;

    public RundownModel(String time, String rundownTitle, int categoryId, String rundownNote, String namaPenanggungJawab, String status) {
        this.time = time;
        this.rundownTitle = rundownTitle;
        this.categoryId = categoryId;
        this.rundownNote = rundownNote;
        this.namaPenanggungJawab = namaPenanggungJawab;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
