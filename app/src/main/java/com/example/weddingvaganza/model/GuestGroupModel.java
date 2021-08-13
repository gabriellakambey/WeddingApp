package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GuestGroupModel implements Serializable {
    private int classId;
    @SerializedName("kelas")
    private String title;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GuestGroupModel(int classId, String title) {
        this.classId = classId;
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
