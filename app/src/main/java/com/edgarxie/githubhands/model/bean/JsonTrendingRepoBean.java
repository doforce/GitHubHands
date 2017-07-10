package com.edgarxie.githubhands.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edgar on 17-6-16.
 */

public class JsonTrendingRepoBean {
    @SerializedName("items")
    @Expose
    private List<TrendingRepoBean> items = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("msg")
    @Expose
    private String msg;

    public List<TrendingRepoBean> getItems() {
        return items;
    }

    public void setItems(List<TrendingRepoBean> items) {
        this.items = items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
