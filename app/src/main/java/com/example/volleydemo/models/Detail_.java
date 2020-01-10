
package com.example.volleydemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Detail_ {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("amountDetails")
    @Expose

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private  ArrayList<AmountDetails>amountDetailsArrayList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ArrayList<AmountDetails> getAmountDetails()
    {
        return amountDetailsArrayList;
    }

    public void setAmountDetails(ArrayList<AmountDetails> amountDetailsArrayList) {
        this.amountDetailsArrayList = amountDetailsArrayList;
    }

}
