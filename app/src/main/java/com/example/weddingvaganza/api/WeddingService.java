package com.example.weddingvaganza.api;

import com.example.weddingvaganza.model.AddCategoryResponse;
import com.example.weddingvaganza.model.AddGuestResponse;
import com.example.weddingvaganza.model.AddRundownResponse;
import com.example.weddingvaganza.model.AddScheduleResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.model.LoginResponseModel;
import com.example.weddingvaganza.model.RundownModel;
import com.example.weddingvaganza.model.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    @GET("category/user/{userId}")
    Call<List<CategoryModel>> getCategory(@Path("userId") int currentUserId);

    @GET("/category/id/{id}")
    Call<List<CategoryModel>> getCategoryById (@Path("id") int categoryId);

    @POST("categoryadd")
    Call<AddCategoryResponse> addCategory(@Query("title") String title, @Query("user") int currentUserId);


    // schedule response
    @GET("todolist/user/{userId}")
    Call<List<ScheduleModel>> getSchedule(@Path("userId") int currentUserId);

    @GET("todolist/category/{categoryId}")
    Call<List<ScheduleModel>> getScheduleByCategory (@Path("categoryId") int currentCategory);

    @GET("todolist/{id}")
    Call<ScheduleModel> getScheduleById (@Path("id") int selectedSchedule);

    @GET("todolist/month={month}&&year={year}")
    Call<List<ScheduleModel>> getScheduleMonthYear (@Path("month") int monthDate, @Path("year") int yearDate);

    @FormUrlEncoded
    @POST("todolistadd")
    Call<AddScheduleResponse> addNewSchedule (@Field("date") String date, @Field("title") String title,
                                              @Field("category") int categoryId, @Field("note") String note,
                                              @Field("user") int currentUserId, @Field("status") String checked,
                                              @Field("month") int monthDate, @Field("year") int yearDate);

    @PUT("todolist/update/{id}")
    Call<ScheduleModel> updateSchedule (@Path("id") int scheduleId, @Body ScheduleModel scheduleModel);


    // rundown response
    @GET("rundownevent/user/{userId}")
    Call<List<RundownModel>> getRundown(@Path("userId") int currentUserId);

    @POST("rundownevent")
    Call<AddRundownResponse> addRundown(@Query("time") String time, @Query("title") String title,
                                        @Query("category") int categoryId, @Query("note") String note,
                                        @Query("pj") String pj, @Query("user") int currentUserId);


    // guests response
    @GET("guestclass/all")
    Call<List<GuestGroupModel>> getGuestGroup();

    @GET("guest/class={classId}&&user={userId}")
    Call<List<GuestModel>> getGuest(@Path("classId") int currentGroup, @Path("userId") int currentUser);

    @GET("guest/id/{id}")
    Call<GuestModel> getGuestDetail (@Path("id") int currentGuestId);

    @POST("guestadd")
    Call<AddGuestResponse> addGuest (@Query("kelas") int kelasId, @Query("nama") String nama,
                                     @Query("noHp") String noHp, @Query("email") String email,
                                     @Query("user") int userId, @Query("alamat") String alamat,
                                     @Query("status") String status);

    @GET("guest/name={guestNama}")
    Call<List<GuestModel>> findGuestName(@Path("guestNama") String guestNama);


}
