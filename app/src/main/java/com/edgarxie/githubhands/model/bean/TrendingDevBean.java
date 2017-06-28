package com.edgarxie.githubhands.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendingDevBean {

    @SerializedName("user_link")
    @Expose
    private String userLink;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("developer_avatar")
    @Expose
    private String developerAvatar;

    private boolean collected;

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDeveloperAvatar() {
        return developerAvatar;
    }

    public void setDeveloperAvatar(String developerAvatar) {
        this.developerAvatar = developerAvatar;
    }
}
