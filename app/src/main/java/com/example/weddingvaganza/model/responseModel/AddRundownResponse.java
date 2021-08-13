package com.example.weddingvaganza.model.responseModel;

import com.example.weddingvaganza.model.RundownModel;

public class AddRundownResponse {
    private String status;
    private RundownModel rundownModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RundownModel getRundownModel() {
        return rundownModel;
    }

    public void setRundownModel(RundownModel rundownModel) {
        this.rundownModel = rundownModel;
    }
}
