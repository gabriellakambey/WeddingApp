package com.example.weddingvaganza.model;

public class UserModel {
//    {
//        "status": "success",
//            "personModel": {
//        "userId": 2,
//                "userName": "ujicoba2",
//                "userEmail": "ujicoba2@gmail.com",
//                "userPassword": "ujicoba2",
//                "userCouple": "ujicoba2",
//                "tglPernikahan": "085312345678",
//                "nomorHp": "20/11/2021"
//    }
//    }

    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userCouple;
    private String tglPernikahan;
    private String nomorHp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCouple() {
        return userCouple;
    }

    public void setUserCouple(String userCouple) {
        this.userCouple = userCouple;
    }

    public String getTglPernikahan() {
        return tglPernikahan;
    }

    public void setTglPernikahan(String tglPernikahan) {
        this.tglPernikahan = tglPernikahan;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }
}
