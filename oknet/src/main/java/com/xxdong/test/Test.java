package com.xxdong.test;

import com.xxdong.ok.OkManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dofor on 2017/7/5.
 */

public class Test {
    public static final String BASE_GITHUB_URL="https://api.github.com";
    public static final String GITHUB_SEARCH_REPO="/search/repositories";
    public static final String TOKEN = "292374b45fac3b73584d1097493e4188a4439b5c";
    public static final String ACCESS_TOKEN = "access_token";

    public static void main(String[] args) {
//       putRepo();
//        deleteRepo();
//        getUser();
        String lang="c++";
        System.out.println(lang.replace("#","-shuo"));
    }

    public static void putRepo(){
        OkManager manager=OkManager.getInstance();
        manager.putAsync(BASE_GITHUB_URL + "/user/starred/catboost/catboost"
                ,ACCESS_TOKEN,TOKEN, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    System.out.println(response.body().string());
                }else {
                    System.err.println(response.body().string());
                }
            }
        });
    }

    public static void deleteRepo(){
        OkManager manager=OkManager.getInstance();
        try {
            manager.deleteAsync(BASE_GITHUB_URL + "/user/starred/catboost/catboost", ACCESS_TOKEN, TOKEN, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        System.out.println(response.body().string());
                    }else {
                        System.err.println(response.body().string());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
