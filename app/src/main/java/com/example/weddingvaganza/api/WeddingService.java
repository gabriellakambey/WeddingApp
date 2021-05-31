package com.example.weddingvaganza.api;

import com.example.weddingvaganza.model.LoginRespondModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeddingService {

    @GET("login")
    Call<LoginRespondModel> login(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Call<LoginRespondModel> register(@Field("nama_user") String name, @Field("nama_pasangan_user") String couple,
                                     @Field("email_user") String email, @Field("password_user") String password,
                                     @Field("tgl_pernikahan") String tanggal, @Field("nomorhp_user") String nomorHp);
}
