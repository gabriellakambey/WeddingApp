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
    Call<LoginResponseModel> register(@Field("name") String name, @Field("phoneNum") String phoneNum,
                                      @Field("email") String email, @Field("password") String password,
                                      @Field("confirmPass") String confirmPass, @Field("couple") String couple,
                                      @Field("date") String date);

    @POST("category")
    Call<AddCategoryResponse> addCategory(@Field("title") String title, @Field("schedule") String schedule,
                                          @Field("date") String date, @Field("note") String note);
}
