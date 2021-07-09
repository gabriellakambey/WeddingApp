package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class AddGuestResponse {
    private String status;
    @SerializedName("guest")
    private GuestModel guestModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GuestModel getGuestModel() {
        return guestModel;
    }

    public void setGuestModel(GuestModel guestModel) {
        this.guestModel = guestModel;
    }
}
