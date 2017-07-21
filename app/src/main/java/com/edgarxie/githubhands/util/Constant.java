package com.edgarxie.githubhands.util;

/**
 * Created by edgar on 17-4-19.
 */

public class Constant {

    public static final String TAG_TRENDING_REPO="1";
    public static final String TAG_TRENDING_DEVELOPER="2";
    public static final String TAG_COLLECTIONS="3";

    public static final String BUNDLE_IS_REPO="4";
    public static final String BUNDLE_REPO="5";
    public static final String BUNDLE_DEVELOPER="6";
    public static final String BUNDLE_WEB_URL="7";
    public static final String BUNDLE_DEV_AVATAR="13";
    public static final String BUNDLE_REPO_DESC="14";
    public static final String BUNDLE_REPO_LANG="15";

    public static final String DEFAULT_ENCODING="utf-8";


    public static final String SHARE_PRE_TOKEN="8";
    public static final String SHARE_PRE_USERNAME="9";
    public static final String SHARE_PRE_EMAIL="10";
    public static final String SHARE_PRE_URL="11";
    public static final String SHARE_PRE_AVATAR="12";

    public static final String COLLECTED="Collected";
    public static final String UNCOLLECTED="Uncollected";

    public interface Collection{
        int REPO = 1;
        int DEVELOPER = 2;
    }
}
