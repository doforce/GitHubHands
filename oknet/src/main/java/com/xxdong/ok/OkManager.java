package com.xxdong.ok;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Authenticator;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by edgar on 17-6-17.
 */

public class OkManager {

    private OkHttpClient okHttpClient;
    private OkHttpClient.Builder builder;
    private static volatile OkManager okManager = null;
    private OkRequest okRequest;
    private static final String AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36";
    private static final long READ_TIMEOUT=20;
    private static final long WRITE_TIMEOUT=20;
    private static final long CONNECT_TIMEOUT=20;


    private OkManager() {
        initOkClient();
        okHttpClient=builder.build();
        okRequest=new OkRequest();
    }

    private OkManager(String headerName,String headerValue) {
        initOkClient();
        okHttpClient=builder.build();
        okRequest=new OkRequest(headerName,headerValue);
    }

    private OkManager(Map<String,String> headers) {
        initOkClient();
        okHttpClient=builder.build();
        okRequest=new OkRequest(headers);
    }

    private void initOkClient(){
        builder = new OkHttpClient.Builder()
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
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
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

    public static OkManager getInstance(Map<String,String> headers) {
        if (okManager == null) {
            synchronized (OkManager.class) {
                if (okManager == null) {
                    okManager = new OkManager(headers);
                }
            }
        }
        return okManager;
    }

    public Response get(String url) throws IOException {
        return get(url, null);
    }

    public Response get(String url, String parameter,String val) throws IOException {
        return get(url,map(parameter,val));
    }

    public Response get(String url, Map<String, String> parameters) throws IOException {
        return okHttpClient.newCall(okRequest.getRequest(url, parameters)).execute();
    }

    public void getAsync(String url, Callback callback) throws IOException {
        getAsync(url, null, callback);
    }

    public void getAsync(String url, String parameter,String val, Callback callback) throws IOException {
        getAsync(url,map(parameter,val),callback);
    }

    public void getAsync(String url, Map<String, String> parameters, Callback callback) throws IOException {
        okManager.okHttpClient.newCall(okRequest.getRequest(url, parameters)).enqueue(callback);
    }

    public Response postJson(String url, String json) throws IOException {
        return okHttpClient.newCall(okRequest.postRequestJson(url, json)).execute();
    }

    public Response post(String url, String parameter,String val) throws IOException {
        return post(url,map(parameter,val));
    }

    public Response post(String url) throws IOException {
        return post(url,null);
    }

    public Response post(String url, Map<String, String> parameters) throws IOException {
        return okHttpClient.newCall(okRequest.postRequest(url, parameters)).execute();
    }

    public Response postBody(String url, RequestBody body) throws IOException {
        return okHttpClient.newCall(okRequest.postRequestBody(url,body)).execute();
    }

    public void postAsyncJson(String url, String json, Callback callback) throws IOException {
        okHttpClient.newCall(okRequest.postRequestJson(url, json)).enqueue(callback);
    }

    public void postAsync(String url, String parameter,String val, Callback callback) throws IOException {
        postAsync(url,map(parameter,val),callback);
    }

    public void postAsync(String url,Callback callback) throws IOException{
        postAsync(url,null,callback);
    }

    public void postAsync(String url, Map<String, String> parameters, Callback callback) throws IOException {
        okHttpClient.newCall(okRequest.postRequest(url, parameters)).enqueue(callback);
    }

    public void postAsyncBody(String url, RequestBody body, Callback callback) throws IOException {
        okHttpClient.newCall(okRequest.postRequestBody(url,body)).enqueue(callback);
    }

    public Response putJson(String url,String json) throws IOException{
        return okHttpClient.newCall(okRequest.putRequestJson(url,json)).execute();
    }

    public Response put(String url,String parameter,String val) throws IOException{
        return put(url,map(parameter,val));
    }

    public Response put(String url,Map<String,String> parameters) throws IOException{
        return okHttpClient.newCall(okRequest.putRequest(url,parameters)).execute();
    }

    public Response putBody(String url, RequestBody body) throws IOException{
        return okHttpClient.newCall(okRequest.putRequestBody(url,body)).execute();
    }

    public void putAsyncJson(String url,String json,Callback callback){
        okHttpClient.newCall(okRequest.putRequestJson(url,json)).enqueue(callback);
    }

    public void putAsync(String url,String parameter,String val,Callback callback){
        putAsync(url,map(parameter,val),callback);
    }

    public void putAsyncBody(String url,RequestBody body,Callback callback){
        okHttpClient.newCall(okRequest.putRequestBody(url,body)).enqueue(callback);
    }

    public void putAsync(String url,Map<String,String> parameters,Callback callback){
        okHttpClient.newCall(okRequest.putRequest(url,parameters)).enqueue(callback);
    }

    public Response deleteJson(String url,String json) throws IOException{
        return okHttpClient.newCall(okRequest.deleteRequestJson(url,json)).execute();
    }

    public Response delete(String url,String parameter,String val) throws IOException{
        return delete(url,map(parameter,val));
    }

    public Response delete(String url) throws IOException{
        return delete(url,null);
    }

    public Response delete(String url,Map<String,String> parameters) throws IOException{
        return okHttpClient.newCall(okRequest.deleteRequest(url,parameters)).execute();
    }

    public Response deleteBody(String url,RequestBody body) throws IOException{
        return okHttpClient.newCall(okRequest.deleteRequestBody(url,body)).execute();
    }

    public void deleteAsyncJson(String url,String json,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.deleteRequestJson(url,json)).enqueue(callback);
    }

    public void deleteAsync(String url,String parameter,String val,Callback callback) throws IOException{
        deleteAsync(url,map(parameter,val),callback);
    }

    public void deleteAsync(String url,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.deleteRequest(url,null)).enqueue(callback);
    }

    public void deleteAsync(String url,Map<String,String> parameters,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.deleteRequest(url,parameters)).enqueue(callback);
    }

    public void deleteAsyncBody(String url,RequestBody body,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.deleteRequestBody(url,body)).enqueue(callback);
    }

    public Response patchJson(String url,String json) throws IOException{
        return okHttpClient.newCall(okRequest.patchRequestJson(url,json)).execute();
    }

    public Response patch(String url,String parameter,String val) throws IOException{
        return patch(url,map(parameter,val));
    }

    public Response patch(String url,Map<String,String> parameters) throws IOException{
         return okHttpClient.newCall(okRequest.patchRequest(url,parameters)).execute();
    }

    public Response patchBody(String url,RequestBody body) throws IOException{
        return okHttpClient.newCall(okRequest.patchRequestBody(url,body)).execute();
    }

    public void patchAsyncJson(String url,String json,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.patchRequestJson(url,json)).enqueue(callback);
    }

    public void patchAsync(String url,String parameter,String val,Callback callback) throws IOException{
        patchAsync(url,map(parameter,val),callback);
    }

    public void patchAsync(String url,Map<String,String> parameters,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.patchRequest(url,parameters)).enqueue(callback);
    }

    public void patchAsyncBody(String url,RequestBody body,Callback callback) throws IOException{
        okHttpClient.newCall(okRequest.patchRequestBody(url,body)).enqueue(callback);
    }

    private Map<String,String> map(String par,String val){
        Map<String,String> p=new HashMap<>();
        p.put(par,val);
        return p;
    }
}
