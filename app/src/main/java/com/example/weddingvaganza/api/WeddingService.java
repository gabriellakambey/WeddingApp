package com.example.weddingvaganza.api;

import com.example.weddingvaganza.model.AddCategoryResponse;
import com.example.weddingvaganza.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeddingService {

    @GET("login")
    Call<LoginResponseModel> login(@Query("email") String email_user, @Query("password") String password_user);

    @POST("register")
    Call<LoginResponseModel> register(@Query("name") String nama_user, @Query("email") String email_user,
                                      @Query("password") String password_user, @Query("nomorHP") String nomorhp_user,
                                      @Query("couple") String nama_pasangan_user, @Query("tanggal") String tgl_pernikahan);

    @POST("category")
    Call<AddCategoryResponse> addCategory(@Query("title") String title, @Query("schedule") String schedule,
                                          @Query("date") String date, @Query("note") String note);
}
