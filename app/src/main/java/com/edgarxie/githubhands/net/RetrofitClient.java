package com.edgarxie.githubhands.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by dofor on 2017/10/12.
 */

public class RetrofitClient {
    private static volatile RetrofitClient sInstance;
    private Retrofit retrofit;

    private RetrofitClient(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient instance(String baseUrl){
        if(null == sInstance){
            synchronized (RetrofitClient.class){
                if(null == sInstance){
                    sInstance = new RetrofitClient(baseUrl);
                }
            }
        }
        return sInstance;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
