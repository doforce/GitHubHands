package com.edgarxie.githubhands.model.bean;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edgar on 17-5-1.
 */

public class TrendingRepoBean {

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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getRepoLink() {
        return repoLink;
    }

    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getAddedStars() {
        return addedStars;
    }

    public void setAddedStars(String addedStars) {
        this.addedStars = addedStars;
    }
}
