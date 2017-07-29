package com.edgarxie.githubhands.model.bean;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by edgar on 17-5-1.
 */

@Entity
public class TrendingRepoBean {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("repo_link")
    @Expose
    private String repoLink;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("avatars")
    @Expose
    @Transient
    private List<String> avatars = null;
    @SerializedName("repo")
    @Expose
    private String repo;
    @SerializedName("forks")
    @Expose
    private String forks;
    @SerializedName("added_stars")
    @Expose
    private String addedStars;

    private String frequency;

    private String avatar;

    @Generated(hash = 410648350)
    public TrendingRepoBean(Long id, String lang, String stars, String repoLink,
            String desc, String repo, String forks, String addedStars,
            String frequency, String avatar) {
        this.id = id;
        this.lang = lang;
        this.stars = stars;
        this.repoLink = repoLink;
        this.desc = desc;
        this.repo = repo;
        this.forks = forks;
        this.addedStars = addedStars;
        this.frequency = frequency;
        this.avatar = avatar;
    }

    @Generated(hash = 2105928235)
    public TrendingRepoBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getStars() {
        return this.stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getRepoLink() {
        return this.repoLink;
    }

    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRepo() {
        return this.repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getForks() {
        return this.forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getAddedStars() {
        return this.addedStars;
    }

    public void setAddedStars(String addedStars) {
        this.addedStars = addedStars;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }
}
