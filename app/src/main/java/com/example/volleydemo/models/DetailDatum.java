
package com.example.volleydemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailDatum {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
