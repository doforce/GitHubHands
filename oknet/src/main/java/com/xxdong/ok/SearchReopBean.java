package com.xxdong.ok;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dofor on 2017/7/5.
 */

public class SearchReopBean {
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("items")
    @Expose
    private List<Items> items;


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public class Items{
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("full_name")
        @Expose
        private String fullName;

        @SerializedName("html_url")
        @Expose
        private String htmlUrl;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("language")
        @Expose
        private String lanuage;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLanuage() {
            return lanuage;
        }

        public void setLanuage(String lanuage) {
            this.lanuage = lanuage;
        }
    }
}
