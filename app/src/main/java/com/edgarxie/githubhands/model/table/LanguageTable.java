package com.edgarxie.githubhands.model.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by edgar on 17-5-3.
 */

@Entity
public class LanguageTable {
    private int position;
    private String language;
    @Generated(hash = 1653453600)
    public LanguageTable(int position, String language) {
        this.position = position;
        this.language = language;
    }
    @Generated(hash = 1214169085)
    public LanguageTable() {
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}
