package com.edgarxie.githubhands.model.bean;


/**
 * Created by edgar on 17-5-1.
 */

public class RepoBean {

    private String repo;
    private String[] avatars;
    private String desc;
    private String lang;
    private String stars;
    private String forks;
    private String added_stars;

    public String getRepo() {
        return repo;
    }

    public String[] getAvatars() {
        return avatars;
    }

    public String getDesc() {
        return desc;
    }

    public String getLang() {
        return lang;
    }

    public String getStars() {
        return stars;
    }

    public String getForks() {
        return forks;
    }

    public String getAdded_stars() {
        return added_stars;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public void setAvatars(String[] avatars) {
        this.avatars = avatars;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public void setAdded_stars(String added_stars) {
        this.added_stars = added_stars;
    }
}
