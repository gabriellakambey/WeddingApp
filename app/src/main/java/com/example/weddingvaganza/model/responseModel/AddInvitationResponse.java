package com.example.weddingvaganza.model.responseModel;

import com.example.weddingvaganza.model.InvitationModel;
import com.google.gson.annotations.SerializedName;

public class AddInvitationResponse {

    private String status;
    @SerializedName("FormInvitation")
    private InvitationModel invitationModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InvitationModel getInvitationModel() {
        return invitationModel;
    }

    public void setInvitationModel(InvitationModel invitationModel) {
        this.invitationModel = invitationModel;
    }
}
