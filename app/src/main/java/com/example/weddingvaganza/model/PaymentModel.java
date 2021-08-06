package com.example.weddingvaganza.model;

public class PaymentModel {
    private String titlePaid;
    private int totalPaid;
    private int idBudget;

    public PaymentModel(String titlePaid, int totalPaid, int idBudget) {
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


    public int getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(int totalPaid) {
        this.totalPaid = totalPaid;
    }

    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }
}
