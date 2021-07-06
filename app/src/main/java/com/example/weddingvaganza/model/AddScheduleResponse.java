package com.example.weddingvaganza.model;

import com.google.gson.annotations.SerializedName;

public class AddScheduleResponse {

    private String status;
    @SerializedName("toDoListSchedule")
    private ScheduleModel scheduleModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }
}
