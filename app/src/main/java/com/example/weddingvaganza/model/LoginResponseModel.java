package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
    private String status;
    private UserModel userModel;

    public LoginResponseModel(String status, UserModel userModel){
        this.status = status;
        this.userModel = userModel;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
