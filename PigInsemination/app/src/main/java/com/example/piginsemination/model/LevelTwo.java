
package com.example.piginsemination.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelTwo {

    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("totalSubTopics")
    @Expose
    private String totalSubTopics;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("subTopics")
    @Expose
    private List<SubTopic> subTopics = null;

    @SerializedName("image")
    @Expose
    private String imageUrl;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTotalSubTopics() {
        return totalSubTopics;
    }

    public void setTotalSubTopics(String totalSubTopics) {
        this.totalSubTopics = totalSubTopics;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SubTopic> getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(List<SubTopic> subTopics) {
        this.subTopics = subTopics;
    }

    public String getImageUrl(){return imageUrl;}

    public void setImageUrl(String imageUrl){this.imageUrl= imageUrl;}


}
