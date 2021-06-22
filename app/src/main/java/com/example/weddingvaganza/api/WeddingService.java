package com.example.weddingvaganza.api;

import com.example.weddingvaganza.model.AddCategoryResponse;
import com.example.weddingvaganza.model.AddRundownResponse;
import com.example.weddingvaganza.model.AddScheduleResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.LoginResponseModel;
import com.example.weddingvaganza.model.RundownModel;
import com.example.weddingvaganza.model.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeddingService {

    @GET("login")
    Call<LoginResponseModel> login(@Query("email") String email_user, @Query("password") String password_user);

    @FormUrlEncoded
    @POST("register")
    Call<LoginResponseModel> register(@Field("name") String nama_user, @Field("nomorHp") String nomorhp_user,
                                      @Field("email") String email_user, @Field("password") String password_user,
                                      @Field("couple") String nama_pasangan_user, @Field("tanggal") String tgl_pernikahan);

    // category response
    @GET("category/all")
    Call<List<CategoryModel>> getAllCategory();

    @GET("category/user/{id}")
    Call<List<CategoryModel>> getCategory(@Path("id") int currentUserId);

    @POST("categoryadd")
    Call<AddCategoryResponse> addCategory(@Query("title") String title, @Query("user") int currentUserId);


    // schedule response
    @GET("todolist/all")
    Call<List<ScheduleModel>> getAllSchedule();

    @GET("todolist/user/{id}")
    Call<List<ScheduleModel>> getSchedule(@Query("id") int currentUserId);

    @GET("todolist/user={id}&category={id}")
    Call<List<ScheduleModel>> getScheduleByCategory(@Path("id") int currentUserId, @Path("id") int currentCategory);

    @FormUrlEncoded
    @POST("todolistadd")
    Call<AddScheduleResponse> addNewSchedule (@Field("date") String date, @Field("title") String title,
                                              @Field("category") int categoryId, @Field("note") String note);


    // rundown response
    @GET("rundown/all")
    Call<List<RundownModel>> getRundown();

    @POST("rundownadd")
    Call<AddRundownResponse> addRundown(@Query("time") String time, @Query("title") String title,
                                        @Query("category") int categoryId, @Query("note") String note,
                                        @Query("pj") String pj);

}
