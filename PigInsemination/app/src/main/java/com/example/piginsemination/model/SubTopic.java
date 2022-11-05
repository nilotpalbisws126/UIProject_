
package com.example.piginsemination.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubTopic {

    @SerializedName("catId")
    @Expose
    private String catId;
    @SerializedName("subtopic")
    @Expose
    private String subtopic;
    @SerializedName("totalSub")
    @Expose
    private String totalSub;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public String getTotalSub() {
        return totalSub;
    }

    public void setTotalSub(String totalSub) {
        this.totalSub = totalSub;
    }

}
