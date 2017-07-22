package com.edgarxie.githubhands.model.bean;

/**
 * Created by dofor on 2017/6/29.
 */

public class CollectionBean {
    public static final int TYPE_REPO=1;
    public static final int TYPE_DEV=2;

    public CollectionBean() {
    }

    private int type;

    private String repoLink;
    private String lang;
    private String desc;
    private String repo;

    private String userLink;

    private String user;
    private String developerAvatar;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRepoLink() {
        return repoLink;
    }

    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
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
