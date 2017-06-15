package com.edgarxie.githubhands.model.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by edgar on 17-6-15.
 */

@Entity
public class TrendingLang {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String lang;

    //1 is selected ,0 is unselected
    @NotNull
    private int selected;

    @Generated(hash = 1525264052)
    public TrendingLang(Long id, String lang, int selected) {
        this.id = id;
        this.lang = lang;
        this.selected = selected;
    }

    @Generated(hash = 2023581860)
    public TrendingLang() {
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

    public int getSelected() {
        return this.selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

}
