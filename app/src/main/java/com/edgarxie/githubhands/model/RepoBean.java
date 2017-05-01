package com.edgarxie.githubhands.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.edgarxie.githubhands.BR;

/**
 * Created by edgar on 17-5-1.
 */

public class RepoBean extends BaseObservable {

    private String repo;
    private String[] avatars;
    private String desc;
    private String lang;
    private String stars;
    private String forks;
    private String added_stars;

    @Bindable
    public String getRepo() {
        return repo;
    }

    @Bindable
    public String[] getAvatars() {
        return avatars;
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    @Bindable
    public String getLang() {
        return lang;
    }

    @Bindable
    public String getStars() {
        return stars;
    }

    @Bindable
    public String getForks() {
        return forks;
    }

    @Bindable
    public String getAdded_stars() {
        return added_stars;
    }

    public void setRepo(String repo) {
        this.repo = repo;
        notifyPropertyChanged(BR.repo);
    }

    public void setAvatars(String[] avatars) {
        this.avatars = avatars;
        notifyPropertyChanged(BR.avatars);
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyPropertyChanged(BR.desc);
    }

    public void setLang(String lang) {
        this.lang = lang;
        notifyPropertyChanged(BR.lang);
    }

    public void setStars(String stars) {
        this.stars = stars;
        notifyPropertyChanged(BR.stars);
    }

    public void setForks(String forks) {
        this.forks = forks;
        notifyPropertyChanged(BR.forks);
    }

    public void setAdded_stars(String added_stars) {
        this.added_stars = added_stars;
        notifyPropertyChanged(BR.added_stars);
    }
}
