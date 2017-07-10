package com.edgarxie.githubhands.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRepoBean {
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
        private String language;

        private boolean collected=false;

        public void setCollected(boolean collected) {
            this.collected = collected;
        }

        public boolean isCollected() {
            return collected;
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

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }