package com.example.volleydemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InnerDetail
{

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("subtitle")
        @Expose
        private String subtitle;
        @SerializedName("amountDetails")
        @Expose
        private int amountDetails;

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

        public int getAmountDetails() {
            return amountDetails;
        }

        public void setAmountDetails(int amountDetails) {
            this.amountDetails = amountDetails;
        }

    }
