package com.edgarxie.githubhands.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendingDevBean {

    @SerializedName("target_link")
    @Expose
    private String targetLink;
    @SerializedName("user_link")
    @Expose
    private String userLink;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("target_desc")
    @Expose
    private String targetDesc;
    @SerializedName("user")
    @Expose
    private String user;

    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetDesc() {
        return targetDesc;
    }

    public void setTargetDesc(String targetDesc) {
        this.targetDesc = targetDesc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
