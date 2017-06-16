package com.edgarxie.githubhands.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.edgarxie.githubhands.util.NetConstants.DEFAULT_TIMEOUT;

public class RetrofitClient {

    private GitHubService mService;

    public RetrofitClient(String baseUrl) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        mService= mRetrofit.create(GitHubService.class);
    }

    public GitHubService getRectService() {
        if (mService != null) {
            return mService;
        }
        return null;
    }
}