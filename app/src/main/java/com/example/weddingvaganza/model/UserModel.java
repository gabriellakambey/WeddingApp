package com.example.weddingvaganza.model;

public class UserModel {
    private int id_user;
    private String nama_user;
    private String nama_pasangan_user;
    private String email_user;
    private String password_user;
    private String tgl_pernikahan;
    private String nomorhp_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getNama_pasangan_user() {
        return nama_pasangan_user;
    }

    public void setNama_pasangan_user(String nama_pasangan_user) {
        this.nama_pasangan_user = nama_pasangan_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getTgl_pernikahan() {
        return tgl_pernikahan;
    }

    public void setTgl_pernikahan(String tgl_pernikahan) {
        this.tgl_pernikahan = tgl_pernikahan;
    }

    public String getNomorhp_user() {
        return nomorhp_user;
    }

    public void setNomorhp_user(String nomorhp_user) {
        this.nomorhp_user = nomorhp_user;
    }
}
