package com.xxdong.ok;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by edgar on 17-6-18.
 */

public class OkManagerBean<T> {
    private OkManager okManager;

    public OkManagerBean(){
        okManager = OkManager.getInstance();
    }

    public OkManagerBean(Map<String,String> headers){
        okManager = OkManager.getInstance(headers);
    }

    public OkManagerBean(String headerName,String headerValue){
        okManager=OkManager.getInstance(headerName,headerValue);
    }

    public void getAsync(String url,Class<T> clazz,OkResponseListener<T> success
            ,OkFailedListener failed) throws IOException {
        getAsync(url,null,clazz,success,failed);
    }

    public void getAsync(String url, String parameter, String val, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException {
        getAsync(url,map(parameter,val),clazz,success,failed);
    }

    public void getAsync(String url, Map<String,String> parameters, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException {
        okManager.getAsync(url,parameters,callback(clazz,success,failed));
    }

    public T get(String url,Class<T> clazz) throws IOException {
        return get(url,null,clazz);
    }

    public T get(String url,String parameter,String val,Class<T> clazz) throws IOException {
        return get(url,map(parameter,val),clazz);
    }

    public T get(String url,Map<String,String> parameters,Class<T> clazz) throws IOException {
        return bean(okManager.get(url,parameters),clazz);
    }

    public T postJson(String url,String json,Class<T> clazz) throws IOException {
       return bean(okManager.postJson(url,json),clazz);
    }

    public T post(String url,String parameter,String val,Class<T> clazz) throws IOException {
        return post(url,map(parameter,val),clazz);
    }

    public T post(String url,Class<T> clazz) throws IOException {
        return post(url,null,clazz);
    }

    public T post(String url,Map<String,String> parameters,Class<T> clazz) throws IOException {
        return bean(okManager.post(url,parameters),clazz);
    }

    public T postBody(String url, RequestBody body, Class<T> clazz) throws IOException {
        return bean(okManager.postBody(url,body),clazz);
    }

    public void postAsyncJson(String url, String json, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException {
        okManager.postAsyncJson(url,json,callback(clazz,success,failed));
    }

    public void postAsync(String url, String parameter, String val, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        postAsync(url,map(parameter,val),clazz,success,failed);
    }

    public void postAsync(String url, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        postAsync(url,null,clazz,success,failed);
    }

    public void postAsync(String url, Map<String,String> parameters, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.postAsync(url,parameters,callback(clazz,success,failed));
    }

    public void postAsyncBody(String url, RequestBody body, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.postAsyncBody(url,body,callback(clazz,success,failed));
    }

    public T putJson(String url,String json,Class<T> calzz) throws IOException{
       return bean(okManager.putJson(url,json),calzz);
    }

    public T put(String url,String parameter,String val,Class<T> calzz) throws IOException{
        return put(url,map(parameter,val),calzz);
    }

    public T put(String url,Map<String,String> parameters,Class<T> calzz) throws IOException{
        return bean(okManager.put(url,parameters),calzz);
    }

    public T putBody(String url,RequestBody body,Class<T> calzz) throws IOException{
        return bean(okManager.putBody(url,body),calzz);
    }

    public void putAsyncJson(String url, String json, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed){
        okManager.putAsyncJson(url,json,callback(clazz,success,failed));
    }

    public void putAsync(String url, String parameter, String val, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed){
        putAsync(url,map(parameter,val),clazz,success,failed);
    }

    public void putAsync(String url, Map<String,String> parameters, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed){
        okManager.putAsync(url,parameters,callback(clazz,success,failed));
    }

    public void putAsyncBody(String url, RequestBody body, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed){
        okManager.putAsyncBody(url,body,callback(clazz,success,failed));
    }

    public T deleteJson(String url,String json,Class<T> clazz) throws IOException{
        return bean(okManager.deleteJson(url,json),clazz);
    }

    public T delete(String url,String parameter,String val,Class<T> clazz) throws IOException{
        return delete(url,map(parameter,val),clazz);
    }

    public T delete(String url,Class<T> clazz) throws IOException{
        return delete(url,null);
    }

    public T delete(String url,Map<String,String> parameters,Class<T> clazz) throws IOException{
        return bean(okManager.delete(url,parameters),clazz);
    }

    public T deleteBody(String url,RequestBody body,Class<T> clazz) throws IOException{
        return bean(okManager.deleteBody(url,body),clazz);
    }

    public void deleteAsyncJson(String url, String json, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.deleteAsyncJson(url,json,callback(clazz,success,failed));
    }

    public void deleteAsync(String url, String parameter, String val, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        deleteAsync(url,map(parameter,val),clazz,success,failed);
    }

    public void deleteAsync(String url, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        deleteAsync(url,null,clazz,success,failed);
    }

    public void deleteAsync(String url, Map<String,String> parameters, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.deleteAsync(url,parameters,callback(clazz,success,failed));
    }

    public void deleteAsyncBody(String url, RequestBody body, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.deleteAsyncBody(url,body,callback(clazz,success,failed));
    }


    public T patchJson(String url,String json,Class<T> clazz) throws IOException{
        return bean(okManager.patchJson(url,json),clazz);
    }

    public T patch(String url,String parameter,String val,Class<T> clazz) throws IOException{
        return patch(url,map(parameter,val),clazz);
    }

    public T patch(String url,Map<String,String> parameters,Class<T> clazz) throws IOException{
        return bean(okManager.patch(url,parameters),clazz);
    }

    public T patchBody(String url,RequestBody body,Class<T> clazz) throws IOException{
        return bean(okManager.patchBody(url,body),clazz);
    }

    public void patchAsyncJson(String url, String json, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.patchAsyncJson(url,json,callback(clazz,success,failed));
    }

    public void patchAsync(String url, String parameter, String val, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        patchAsync(url,map(parameter,val),clazz,success,failed);
    }

    public void patchAsync(String url, Map<String,String> parameters, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.patchAsync(url,parameters,callback(clazz,success,failed));
    }

    public void patchAsyncBody(String url, RequestBody body, Class<T> clazz
            , OkResponseListener<T> success, OkFailedListener failed) throws IOException{
        okManager.patchAsyncBody(url,body,callback(clazz,success,failed));
    }

    private Callback callback(final Class<T> clazz, final OkResponseListener<T> success, final OkFailedListener failed){
       return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (failed!=null) {
                    failed.failed(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                success.response(bean(response,clazz));
            }
        };
    }

    private T bean(Response response, Class<T> clazz) throws IOException{
        Gson gson=new Gson();
        if (response.isSuccessful()){
            return gson.fromJson(response.body().string(),clazz);
        }
        return null;
    }

    private Map<String,String> map(String par,String val){
        Map<String,String> p=new HashMap<>();
        p.put(par,val);
        return p;
    }
}
