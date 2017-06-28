package com.xxdong.ok;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by edgar on 17-6-18.
 */

public class OkManagerBean<T> {
    private OkManager okManager;

    public OkManagerBean(){
        okManager = OkManager.getInstance();
    }

    public void getAsync(String url,Class<T> clazz,OkSuccessListener<T> success
            ,OkFailedListener failed) throws IOException {
        getAsync(url,null,clazz,success,failed);
    }

    public void getAsync(String url,Class<T> clazz,OkSuccessListener<T> success) throws IOException {
        getAsync(url,null,clazz,success,null);
    }

    public void getAsync(String url, Map<String,String> parameters, Class<T> clazz
            , OkSuccessListener<T> success, OkFailedListener failed) throws IOException {
        enqueue(url, okManager.getRequest().buildGetRequest(url,parameters),clazz,success,failed);
    }

    public void getAsync(String url,Map<String,String> parameters,Class<T> clazz
            ,OkSuccessListener<T> success) throws IOException {
        enqueue(url, okManager.getRequest().buildGetRequest(url,parameters),clazz,success,null);
    }

    public T get(String url,Class<T> clazz) throws IOException {
        return get(url,null,clazz);
    }

    public T get(String url,Map<String,String> parameters,Class<T> clazz) throws IOException {
        Response resp= okManager.getOkHttpClient().newCall(okManager.getRequest().buildGetRequest(url,parameters)).execute();
        if (resp.isSuccessful()){
            return getBean(resp.body().string(),clazz);
        }
        return null;
    }

    public T post(String url,Map<String,String> parameters,Class<T> clazz) throws IOException {
        Response resp= okManager.getOkHttpClient().newCall(okManager.getRequest().builderPostRequest(url,parameters)).execute();
        if (resp.isSuccessful()){
            return getBean(resp.body().string(),clazz);
        }
        return null;
    }

    public T post(String url,String json,Class<T> clazz) throws IOException {
        Response resp= okManager.getOkHttpClient().newCall(okManager.getRequest().builderPostRequest(url,json)).execute();
        if (resp.isSuccessful()){
            return getBean(resp.body().string(),clazz);
        }
        return null;
    }

    public void postAsync(String url,Map<String,String> parameters,Class<T> clazz
            ,OkSuccessListener<T> success,OkFailedListener failed) throws IOException{
        enqueue(url, okManager.getRequest().builderPostRequest(url,parameters),clazz,success,failed);
    }

    public void postAsync(String url,Map<String,String> parameters,Class<T> clazz
            ,OkSuccessListener<T> success) throws IOException{
        enqueue(url, okManager.getRequest().builderPostRequest(url,parameters),clazz,success,null);
    }

    public void postAsync(String url,String json,Class<T> clazz
            ,OkSuccessListener<T> success,OkFailedListener failed) throws IOException {
        enqueue(url, okManager.getRequest().builderPostRequest(url,json),clazz,success,failed);
    }

    public void postAsync(String url,String json,Class<T> clazz
            ,OkSuccessListener<T> success) throws IOException {
        enqueue(url, okManager.getRequest().builderPostRequest(url,json),clazz,success,null);
    }

    private void enqueue(String url, Request request, final Class<T> clazz
            , final OkSuccessListener<T> success, final OkFailedListener failed) throws IOException {
        okManager.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (failed!=null) {
                    failed.failed(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                success.success(getBean(response.body().string(),clazz));
            }
        });
    }

    private T getBean(String json,Class<T> clazz){
        Gson gson=new Gson();
        return gson.fromJson(json,clazz);
    }
}
