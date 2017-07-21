package com.edgarxie.githubhands.model.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dofor on 2017/6/28.
 */

@Entity
public class RepoCollection {
    @Id(autoincrement = true)
    private Long id;

    private String repoLink;
    private String lang;
    private String desc;
    private String repo;
    @Generated(hash = 247305813)
    public RepoCollection(Long id, String repoLink, String lang, String desc,
            String repo) {
        this.id = id;
        this.repoLink = repoLink;
        this.lang = lang;
        this.desc = desc;
        this.repo = repo;
    }
    @Generated(hash = 1733371367)
    public RepoCollection() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
}
