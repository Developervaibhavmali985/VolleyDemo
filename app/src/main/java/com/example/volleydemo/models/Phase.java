
package com.example.volleydemo.models;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phase {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("detail")
    @Expose
    private List<Detail> detail = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

}
