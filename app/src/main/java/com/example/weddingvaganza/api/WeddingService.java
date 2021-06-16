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
import retrofit2.http.Query;

public interface WeddingService {

    @GET("login")
    Call<LoginResponseModel> login(@Query("email") String email_user, @Query("password") String password_user);

    @FormUrlEncoded
    @POST("register")
    Call<LoginResponseModel> register(@Field("name") String nama_user, @Field("nomorHp") String nomorhp_user,
                                      @Field("email") String email_user, @Field("password") String password_user,
                                      @Field("couple") String nama_pasangan_user, @Field("tanggal") String tgl_pernikahan);

    @GET("category2/all")
    Call<List<CategoryModel>> getCategory();

    @POST("categoryadd2")
    Call<AddCategoryResponse> addCategory(@Query("title") String title);

    @GET("todolist/all")
    Call<List<ScheduleModel>> getAllSchedule();

    @FormUrlEncoded
    @POST("todolistadd")
    Call<AddScheduleResponse> addNewSchedule (@Field("date") String date, @Field("title") String title,
                                              @Field("category") int categoryId, @Field("note") String note);

    @GET("rundown/all")
    Call<List<RundownModel>> getRundown();

    @POST("rundownadd")
    Call<AddRundownResponse> addRundown(@Query("time") String time, @Query("title") String title,
                                        @Query("category") int categoryId, @Query("note") String note,
                                        @Query("pj") String pj);

}
