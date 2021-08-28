package com.example.weddingvaganza.api;

import com.example.weddingvaganza.model.CategoryRundownModel;
import com.example.weddingvaganza.model.OurBudgetModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.model.responseModel.AddBudgetResponse;
import com.example.weddingvaganza.model.responseModel.AddCategoryResponse;
import com.example.weddingvaganza.model.responseModel.AddGuestResponse;
import com.example.weddingvaganza.model.responseModel.AddInvitationResponse;
import com.example.weddingvaganza.model.responseModel.AddRundownResponse;
import com.example.weddingvaganza.model.responseModel.AddScheduleResponse;
import com.example.weddingvaganza.model.BudgetModel;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.model.GuestUpdateModel;
import com.example.weddingvaganza.model.InvitationModel;
import com.example.weddingvaganza.model.LoginResponseModel;
import com.example.weddingvaganza.model.RundownModel;
import com.example.weddingvaganza.model.ScheduleModel;
import com.example.weddingvaganza.model.ScheduleUpdateModel;
import com.example.weddingvaganza.model.schedulebyid.ScheduleByIdModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
                                      @Field("couple") String nama_pasangan_user, @Field("tanggal") String tgl_pernikahan,
                                      @Field("budget") int budget);

    // CATEGORY RESPONSE
    @GET("category/user/{userId}")
    Call<List<CategoryModel>> getCategory(@Path("userId") int currentUserId);

    @GET("/category/id/{id}")
    Call<List<CategoryModel>> getCategoryById (@Path("id") int categoryId);

    @POST("categoryadd")
    Call<AddCategoryResponse> addCategory(@Query("title") String title, @Query("user") int currentUserId);


    // SCHEDULE RESPONSE
    @GET("todolist/user/{userId}")
    Call<List<ScheduleModel>> getSchedule(@Path("userId") int currentUserId);

    @GET("todolist/category/{categoryId}")
    Call<List<ScheduleModel>> getScheduleByCategory (@Path("categoryId") int currentCategory);

    @GET("todolist/{id}")
    Call<ScheduleByIdModel> getScheduleById (@Path("id") int selectedSchedule);

    @GET("todolist/{month}/{year}/{categoryId}")
    Call<List<ScheduleModel>> getScheduleMonthYear (@Path("month") int monthDate, @Path("year") int yearDate, @Path("categoryId") int categoryId);

    @FormUrlEncoded
    @POST("todolistadd")
    Call<AddScheduleResponse> addNewSchedule (@Field("date") String date, @Field("title") String title,
                                              @Field("category") int categoryId, @Field("note") String note,
                                              @Field("user") int currentUserId, @Field("status") String checked,
                                              @Field("month") int monthDate, @Field("year") int yearDate);

    @PUT("todolist/update/{id}")
    Call<ScheduleModel> updateSchedule (@Path("id") int scheduleId, @Body ScheduleUpdateModel scheduleModel);


    // RUNDOWN RESPONSE
    @GET("rundownevent/user/{userId}")
    Call<List<RundownModel>> getRundown(@Path("userId") int currentUserId);

    @POST("rundownevent")
    Call<AddRundownResponse> addRundown(@Query("time") String time, @Query("title") String title,
                                        @Query("category") int categoryId, @Query("note") String note,
                                        @Query("pj") String pj, @Query("user") int currentUserId,
                                        @Query("status") String status);

    @GET("rundowncategory/{userId}")
    Call<List<CategoryRundownModel>> getRundownCategory(@Path("userId") int userId);



    // GUEST RESPONSE
    @GET("guestclass/all")
    Call<List<GuestGroupModel>> getGuestGroup();

    @GET("guest/user/{userId}")
    Call<List<GuestModel>> getGuestByUserId(@Path("userId") int userId);

    @GET("guest/class={classId}&&user={userId}")
    Call<List<GuestModel>> getGuest(@Path("classId") int currentGroup, @Path("userId") int currentUser);

    @GET("guest/id/{id}")
    Call<GuestModel> getGuestById (@Path("id") int currentGuestId);

    @PUT("guest/update/{id}")
    Call<GuestUpdateModel> putStatus (@Path("id") int guestId, @Body GuestUpdateModel guestUpdateModel);

    @POST("guestadd")
    Call<AddGuestResponse> addGuest (@Query("kelas") int kelasId, @Query("nama") String nama,
                                     @Query("noHp") String noHp, @Query("email") String email,
                                     @Query("user") int userId, @Query("alamat") String alamat,
                                     @Query("status") String status);

    @GET("guest/class={classId}&&name={guestNama}")
    Call<List<GuestModel>> findGuestName(@Path("classId") int classId, @Path("guestNama") String guestNama);

    @GET("guest/status={status}&&user={userId}")
    Call<List<GuestModel>> getInvitedGuest(@Path("status") String status, @Path("userId") int userId);



    // INVITATION RESPONSE
    @GET("forminvitation/user/{userId}")
    Call<List<InvitationModel>> getInvitation (@Path("userId") int userId);

    @POST("forminvitationadd")
    Call<AddInvitationResponse> postInvitation (@Query("grooms") String grooms, @Query("groomsFather") String groomsFather, @Query("groomsMother") String groomsMother,
                                                @Query("brides") String brides, @Query("bridesFather") String bridesFather, @Query("bridesMother") String bridesMother,
                                                @Query("category") int category, @Query("date") String date, @Query("time") String time, @Query("locationTitle") String locationTitle,
                                                @Query("longitude") double longitude, @Query("latitude") double latitude, @Query("note") String note, @Query("user") int user, @Query("template") String template);

    @PUT("invitation/update/{id}")
    Call<InvitationModel> updateTemplate (@Path("id") int id, @Body InvitationModel invitationModel);

    @DELETE("forminvitation/delete/{id}")
    Call<InvitationModel> deleteInvitation (@Path("id") int invitationId);



    // BUDGET RESPONSE
    @GET("budgetlist/cost/total={userId}")
    Call<Integer> getBudgetTotal (@Path("userId") int userId);

    @GET("budgetlist/user/{userId}")
    Call<List<BudgetModel>> getBudgetList (@Path("userId") int userId);

    @POST("budgetlist/add")
    Call<AddBudgetResponse> addBudget(@Query("title") String title, @Query("cost") int cost,
                                      @Query("note") String note, @Query("status") String status,
                                      @Query("paid") int paid, @Query("user") int user);

    @PUT("budgetlist/update/{id}")
    Call<BudgetModel> updateStatusBudget(@Path("id") int id, @Body BudgetModel budgetModel);



    // OUR BUDGET RESPONSE
    @GET("person/id/{id}")
    Call<UserModel> getOurBudget(@Path("id") int id);

    @PUT("ourbudget/update/{id}")
    Call<OurBudgetModel> updateOurBudget(@Path("id") int id, @Body OurBudgetModel ourBudgetModel);



    // COST RESPONSE
    @GET("budgetlist/paid/status={status}&&user={userId}")
    Call<Integer> getCostTotal (@Path("status") String status, @Path("userId") int userId);




}
