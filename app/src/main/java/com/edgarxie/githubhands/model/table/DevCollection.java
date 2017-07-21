package com.edgarxie.githubhands.model.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dofor on 2017/6/28.
 */

@Entity
public class DevCollection {
    @Id(autoincrement = true)
    private Long id;

    private String userLink;
    private String user;
    private String developerAvatar;
    @Generated(hash = 61846163)
    public DevCollection(Long id, String userLink, String user,
            String developerAvatar) {
        this.id = id;
        this.userLink = userLink;
        this.user = user;
        this.developerAvatar = developerAvatar;
    }
    @Generated(hash = 1859781909)
    public DevCollection() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
