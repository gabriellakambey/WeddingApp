package com.example.weddingvaganza.api;

import com.example.weddingvaganza.model.LoginRespondModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeddingService {

    @GET("login")
    Call<LoginRespondModel> login(@Query("email_user") String email_user, @Query("password_user") String password_user);

    @POST("register")
    Call<LoginRespondModel> register(@Field("nama_user") String nama_user, @Field("nama_pasangan_user") String nama_pasangan_user,
                                     @Field("email_user") String email_user, @Field("password_user") String password_user,
                                     @Field("tgl_pernikahan") String tgl_pernikahan, @Field("nomorhp_user") String nomorhp_user);
}
