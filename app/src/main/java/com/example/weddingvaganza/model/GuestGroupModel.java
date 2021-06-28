package com.example.weddingvaganza.model;

import java.io.Serializable;

public class GuestGroupModel implements Serializable {
    private int classId;
    private String kelas;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public GuestGroupModel(int classId, String kelas) {
        this.classId = classId;
        this.kelas = kelas;
    }
}
