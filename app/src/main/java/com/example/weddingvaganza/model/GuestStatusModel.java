package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class GuestStatusModel {

    private int guestId;
    private int classId;
    private String guestNama;
    private String guestNoHp;
    private String guestEmail;
    private int userId;
    private String homeAddress;
    private String status;

    public GuestStatusModel(String status) {
        this.status = status;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getGuestNama() {
        return guestNama;
    }

    public void setGuestNama(String guestNama) {
        this.guestNama = guestNama;
    }

    public String getGuestNoHp() {
        return guestNoHp;
    }

    public void setGuestNoHp(String guestNoHp) {
        this.guestNoHp = guestNoHp;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
