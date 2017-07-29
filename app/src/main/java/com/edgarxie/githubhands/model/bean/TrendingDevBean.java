package com.edgarxie.githubhands.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TrendingDevBean {

    @Id(autoincrement = true)
    private Long id;

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

    private String frequency;

    @Generated(hash = 2018860501)
    public TrendingDevBean(Long id, String userLink, String fullName, String user,
            String developerAvatar, String frequency) {
        this.id = id;
        this.userLink = userLink;
        this.fullName = fullName;
        this.user = user;
        this.developerAvatar = developerAvatar;
        this.frequency = frequency;
    }

    @Generated(hash = 1791818331)
    public TrendingDevBean() {
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
