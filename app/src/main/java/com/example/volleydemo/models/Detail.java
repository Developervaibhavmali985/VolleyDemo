
package com.example.volleydemo.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("detail")
    @Expose
    private List<Detail_> detail = null;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Detail_> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail_> detail)
    {
        this.detail = detail;
    }

}
