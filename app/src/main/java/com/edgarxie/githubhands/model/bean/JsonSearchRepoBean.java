package com.edgarxie.githubhands.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dofor on 2017/7/5.
 */

public class JsonSearchRepoBean {
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("items")
    @Expose
    private List<SearchRepoBean> items;


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<SearchRepoBean> getItems() {
        return items;
    }

    public void setItems(List<SearchRepoBean> items) {
        this.items = items;
    }
}
