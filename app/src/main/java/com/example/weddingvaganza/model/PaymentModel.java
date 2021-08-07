package com.example.weddingvaganza.model;

public class PaymentModel {
    private String titlePaid;
    private String totalPaid;
    private String idBudget;

    public PaymentModel(String titlePaid, String totalPaid, String idBudget) {
        this.titlePaid = titlePaid;
        this.totalPaid = totalPaid;
        this.idBudget = idBudget;
    }

    public String getTitlePaid() {
        return titlePaid;
    }

    public void setTitlePaid(String titlePaid) {
        this.titlePaid = titlePaid;
    }


    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(String idBudget) {
        this.idBudget = idBudget;
    }
}
