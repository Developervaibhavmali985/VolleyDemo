
package com.example.volleydemo.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("estimateName")
    @Expose
    private String estimateName;
    @SerializedName("estimateAmount")
    @Expose
    private Integer estimateAmount;
    @SerializedName("estimateDate")
    @Expose
    private String estimateDate;
    @SerializedName("phase")
    @Expose
    private List<Phase> phase = null;

    public String getEstimateName() {
        return estimateName;
    }

    public void setEstimateName(String estimateName) {
        this.estimateName = estimateName;
    }

    public Integer getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(Integer estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public String getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(String estimateDate) {
        this.estimateDate = estimateDate;
    }

    public List<Phase> getPhase() {
        return phase;
    }

    public void setPhase(List<Phase> phase) {
        this.phase = phase;
    }

}
