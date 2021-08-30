package com.example.weddingvaganza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class InvitationModel implements Parcelable {

    @SerializedName("formInvitationId")
    private int idInvitation;

    private String groomsName;

    @SerializedName("groomsFatherName")
    private String groomsFather;

    @SerializedName("groomsMotherName")
    private String groomsMother;

    private String bridesName;

    @SerializedName("bridesFatherName")
    private String bridesFather;

    @SerializedName("bridesMotherName")
    private String bridesMother;

    @SerializedName("categoryId")
    private int idCategory;

    @SerializedName("dateFormInvitation")
    private String dateInvitation;

    @SerializedName("timeFormInvitation")
    private String timeInvitation;

    @SerializedName("titleLocation")
    private String titleLocation;

    private double longitude;

    private double latitude;

    @SerializedName("noteFormInvitation")
    private String noteInvitation;

	@SerializedName("userId")
	private int userId;

	private String template;

	private CategoryModel categoryModel;

    public InvitationModel(int idInvitation, String groomsName, String groomsFather, String groomsMother, String bridesName, String bridesFather, String bridesMother, int idCategory, String dateInvitation, String timeInvitation, String titleLocation, double longitude, double latitude, String noteInvitation, int userId, String template) {
        this.idInvitation = idInvitation;
        this.groomsName = groomsName;
        this.groomsFather = groomsFather;
        this.groomsMother = groomsMother;
        this.bridesName = bridesName;
        this.bridesFather = bridesFather;
        this.bridesMother = bridesMother;
        this.idCategory = idCategory;
        this.dateInvitation = dateInvitation;
        this.timeInvitation = timeInvitation;
        this.titleLocation = titleLocation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.noteInvitation = noteInvitation;
        this.userId = userId;
        this.template = template;
    }

    protected InvitationModel(Parcel in) {
        idInvitation = in.readInt();
        groomsName = in.readString();
        groomsFather = in.readString();
        groomsMother = in.readString();
        bridesName = in.readString();
        bridesFather = in.readString();
        bridesMother = in.readString();
        idCategory = in.readInt();
        dateInvitation = in.readString();
        timeInvitation = in.readString();
        titleLocation = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        noteInvitation = in.readString();
        userId = in.readInt();
        template = in.readString();
    }

    public static final Creator<InvitationModel> CREATOR = new Creator<InvitationModel>() {
        @Override
        public InvitationModel createFromParcel(Parcel in) {
            return new InvitationModel(in);
        }

        @Override
        public InvitationModel[] newArray(int size) {
            return new InvitationModel[size];
        }
    };

    public int getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(int idInvitation) {
        this.idInvitation = idInvitation;
    }

    public String getGroomsName() {
        return groomsName;
    }

    public void setGroomsName(String groomsName) {
        this.groomsName = groomsName;
    }

    public String getGroomsFather() {
        return groomsFather;
    }

    public void setGroomsFather(String groomsFather) {
        this.groomsFather = groomsFather;
    }

    public String getGroomsMother() {
        return groomsMother;
    }

    public void setGroomsMother(String groomsMother) {
        this.groomsMother = groomsMother;
    }

    public String getBridesName() {
        return bridesName;
    }

    public void setBridesName(String bridesName) {
        this.bridesName = bridesName;
    }

    public String getBridesFather() {
        return bridesFather;
    }

    public void setBridesFather(String bridesFather) {
        this.bridesFather = bridesFather;
    }

    public String getBridesMother() {
        return bridesMother;
    }

    public void setBridesMother(String bridesMother) {
        this.bridesMother = bridesMother;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDateInvitation() {
        return dateInvitation;
    }

    public void setDateInvitation(String dateInvitation) {
        this.dateInvitation = dateInvitation;
    }

    public String getTimeInvitation() {
        return timeInvitation;
    }

    public void setTimeInvitation(String timeInvitation) {
        this.timeInvitation = timeInvitation;
    }

    public String getTitleLocation() {
        return titleLocation;
    }

    public void setTitleLocation(String titleLocation) {
        this.titleLocation = titleLocation;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNoteInvitation() {
        return noteInvitation;
    }

    public void setNoteInvitation(String noteInvitation) {
        this.noteInvitation = noteInvitation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idInvitation);
        dest.writeString(groomsName);
        dest.writeString(groomsFather);
        dest.writeString(groomsMother);
        dest.writeString(bridesName);
        dest.writeString(bridesFather);
        dest.writeString(bridesMother);
        dest.writeInt(idCategory);
        dest.writeString(dateInvitation);
        dest.writeString(timeInvitation);
        dest.writeString(titleLocation);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(noteInvitation);
        dest.writeInt(userId);
        dest.writeString(template);
    }
}
