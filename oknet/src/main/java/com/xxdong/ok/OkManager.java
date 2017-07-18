package com.xxdong.ok;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by edgar on 17-6-17.
 */

public class OkManager {

    private OkHttpClient okHttpClient;
    private static volatile OkManager okManager = null;
    private OkRequest okRequest;
    private Response response;
    private static final String AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36";
    private static final long READ_TIMEOUT=20;
    private static final long WRITE_TIMEOUT=20;
    private static final long CONNECT_TIMEOUT=20;


    private OkManager() {
        initOkClient();
        okRequest=OkRequest.getInstance();
    }

    private OkManager(String headerName,String headerValue) {
        initOkClient();
        okRequest=OkRequest.getInstance(headerName,headerValue);
    }

    private void initOkClient(){
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request()
                                .newBuilder().addHeader("User-Agent", AGENT).build());
                    }
                })
                .followRedirects(true)
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
                .build();
    }

    public static OkManager getInstance() {
        if (okManager == null) {
            synchronized (OkManager.class) {
                if (okManager == null) {
                    okManager = new OkManager();
                }
            }
        }
        return okManager;
    }

    public static OkManager getInstance(String headerName,String headerValue) {
        if (okManager == null) {
            synchronized (OkManager.class) {
                if (okManager == null) {
                    okManager = new OkManager(headerName,headerValue);
                }
            }
        }
        return okManager;
    }

    public String get(String url) throws IOException {
        return get(url, null);
    }

    public String get(String url, Map<String, String> parameters) throws IOException {
        response = okHttpClient.newCall(okRequest.getRequest(url, parameters)).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    public String get(String url, String key,String val) throws IOException {
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        return get(url,p);
    }

    public void getAsync(String url, Callback callback) throws IOException {
        getAsync(url, null, callback);
    }

    public void getAsync(String url, String key,String val, Callback callback) throws IOException {
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        getAsync(url,p,callback);
    }

    public void getAsync(String url, Map<String, String> parameters, Callback callback) throws IOException {
        okManager.okHttpClient.newCall(okRequest.getRequest(url, parameters)).enqueue(callback);
    }

    public String postJson(String url, String json) throws IOException {
        response = okHttpClient.newCall(okRequest.postRequestJson(url, json)).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    public String post(String url, String key,String val) throws IOException {
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        return post(url,p);
    }

    public String post(String url) throws IOException {
        return post(url,null);
    }


    public String post(String url, Map<String, String> parameters) throws IOException {
        response = okHttpClient.newCall(okRequest.postRequest(url, parameters)).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    public void postAsyncJson(String url, String json, Callback callback) throws IOException {
        okHttpClient.newCall(okRequest.postRequestJson(url, json)).enqueue(callback);
    }

    public void postAsync(String url, String key,String val, Callback callback) throws IOException {
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        postAsync(url,p,callback);
    }

    public void postAsync(String url,Callback callback) throws IOException{
        postAsync(url,null,callback);
    }

    public void postAsync(String url, Map<String, String> parameters, Callback callback) throws IOException {
        okHttpClient.newCall(okRequest.postRequest(url, parameters)).enqueue(callback);
    }

    public String putJson(String url,String json) throws IOException{
        response=okHttpClient.newCall(okRequest.putRequestJson(url,json)).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }

    public String put(String url,String key,String val) throws IOException{
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        return put(url,p);
    }

    public String put(String url,Map<String,String> parameters) throws IOException{
        response=okHttpClient.newCall(okRequest.putRequest(url,parameters)).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }

    public void putAsyncJson(String url,String json,Callback callback){
        okHttpClient.newCall(okRequest.putRequestJson(url,json)).enqueue(callback);
    }

    public void putAsync(String url,String key,String val,Callback callback){
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        putAsync(url,p,callback);
    }

    public void putAsync(String url,Map<String,String> parameters,Callback callback){
        okHttpClient.newCall(okRequest.putRequest(url,parameters)).enqueue(callback);
    }

    public String deleteJson(String url,String json) throws IOException{
        response=okHttpClient.newCall(okRequest.deleteRequestJson(url,json)).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }

    public String delete(String url,String key,String val) throws IOException{
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        return delete(url,p);
    }

    public String delete(String url) throws IOException{
        return delete(url,null);
    }

    public String delete(String url,Map<String,String> parameters) throws IOException{
        response=okHttpClient.newCall(okRequest.deleteRequest(url,parameters)).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }

    public void deleteAsyncJson(String url,String json,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.deleteRequestJson(url,json)).enqueue(callback);
    }

    public void deleteAsync(String url,String key,String val,Callback callback) throws IOException{
        Map<String,String> p=new HashMap<>();
        p.put(key,val);
        deleteAsync(url,p,callback);
    }

    public void deleteAsync(String url,Callback callback) throws IOException{
        deleteAsync(url,null,callback);
    }

    public void deleteAsync(String url,Map<String,String> parameters,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.deleteRequest(url,parameters)).enqueue(callback);
    }

    //// TODO: 17-7-19 patch request

    public OkRequest getOkRequest() {
        return okRequest;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
