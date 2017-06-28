package com.edgarxie.githubhands.model.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dofor on 2017/6/28.
 */

@Entity
public class Collection {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private boolean isRepo;

    private String repoLink;
    private String lang;
    private String desc;
    private String repo;

    private String userLink;
    private String user;
    private String developerAvatar;
    @Generated(hash = 2100921884)
    public Collection(Long id, boolean isRepo, String repoLink, String lang,
            String desc, String repo, String userLink, String user,
            String developerAvatar) {
        this.id = id;
        this.isRepo = isRepo;
        this.repoLink = repoLink;
        this.lang = lang;
        this.desc = desc;
        this.repo = repo;
        this.userLink = userLink;
        this.user = user;
        this.developerAvatar = developerAvatar;
    }
    @Generated(hash = 1149123052)
    public Collection() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getIsRepo() {
        return this.isRepo;
    }
    public void setIsRepo(boolean isRepo) {
        this.isRepo = isRepo;
    }
    public String getRepoLink() {
        return this.repoLink;
    }
    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }
    public String getLang() {
        return this.lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
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
    public String getUserLink() {
        return this.userLink;
    }
    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }
    public String getUser() {
        return this.user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getDeveloperAvatar() {
        return this.developerAvatar;
    }
    public void setDeveloperAvatar(String developerAvatar) {
        this.developerAvatar = developerAvatar;
    }
}
