package com.xxdong.ok;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dofor on 2017/7/5.
 */

public class Test {
    public static final String BASE_GITHUB_URL="https://api.github.com";
    public static final String GITHUB_SEARCH_REPO="/search/repositories";

    public static void main(String[] args) {
//        githubSearch();
        githubSearchBean();
    }

    public static void githubSearchBean(){
//        final OkManagerBean<SearchReopBean> managerBean=new OkManagerBean<>();
//        Map<String,String> p=new HashMap<>();
//        p.put("q","trending");
//        try {
//            managerBean.getAsync(BASE_GITHUB_URL + GITHUB_SEARCH_REPO, p, SearchReopBean.class,
//                    new OkSuccessListener<SearchReopBean>() {
//                        @Override
//                        public void success(SearchReopBean data) {
//                            System.out.println(data.getTotalCount());
//                            System.out.println(data.getItems().get(0).getDescription());
//                            System.out.println(data.getItems().get(0).getFullName());
//                            System.out.println(data.getItems().get(0).getHtmlUrl());
//                            System.out.println(data.getItems().get(0).getLanuage());
//                            System.out.println(data.getItems().get(0).getName());
//                        }
//                    }, new OkFailedListener() {
//                        @Override
//                        public void failed(String msg) {
//                            System.err.println(msg);
//                        }
//                    });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void githubSearch(){
        OkManager manager=OkManager.getInstance();
        Map<String,String> p=new HashMap<>();
        p.put("q","trending");
        try {
            manager.getAsync(BASE_GITHUB_URL+GITHUB_SEARCH_REPO
                    , p, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.err.println(e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println(response.body().string());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
