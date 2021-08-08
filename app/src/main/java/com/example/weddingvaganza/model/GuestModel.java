package com.example.weddingvaganza.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GuestModel implements Parcelable {
    private int guestId;
    private int classId;
    private String guestNama;
    private String guestNoHp;
    private String guestEmail;
    private int userId;
    private String homeAddress;
    private String status;

    protected GuestModel(Parcel in) {
        guestId = in.readInt();
        classId = in.readInt();
        guestNama = in.readString();
        guestNoHp = in.readString();
        guestEmail = in.readString();
        userId = in.readInt();
        homeAddress = in.readString();
        status = in.readString();
    }

    public static final Creator<GuestModel> CREATOR = new Creator<GuestModel>() {
        @Override
        public GuestModel createFromParcel(Parcel in) {
            return new GuestModel(in);
        }

        @Override
        public GuestModel[] newArray(int size) {
            return new GuestModel[size];
        }
    };

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(guestId);
        dest.writeInt(classId);
        dest.writeString(guestNama);
        dest.writeString(guestNoHp);
        dest.writeString(guestEmail);
        dest.writeInt(userId);
        dest.writeString(homeAddress);
        dest.writeString(status);
    }
}
