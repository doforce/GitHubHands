package com.xxdong.ok;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by edgar on 17-6-17.
 */

public class OkManager {

    private OkHttpClient okHttpClient;
    private static volatile OkManager okManager = null;
    private OkRequest request;
    private static final String AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36";
    private static long READ_TIMEOUT=20;
    private static long WRITE_TIMEOUT=20;
    private static long CONNECT_TIMEOUT=20;


    private OkManager() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .hostnameVerifier((s, sslSession) -> true)
                .addInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder().addHeader("User-Agent", AGENT).build()))
                .followRedirects(true)
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
                .build();
        request = OkRequest.getInstance();
    }

    public String get(String url) throws IOException {
        return get(url, null);
    }

    public String get(String url, Map<String, String> parameters) throws IOException {
        Response response = okHttpClient.newCall(request.buildGetRequest(url, parameters)).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    public void getAsync(String url, Callback callback) throws IOException {
        getAsync(url, null, callback);
    }

    public void getAsync(String url, Map<String, String> parameters, Callback callback) throws IOException {
        okManager.okHttpClient.newCall(request.buildGetRequest(url, parameters)).enqueue(callback);
    }

    public String post(String url, String json) throws IOException {
        Response resp = okHttpClient.newCall(request.builderPostRequest(url, json)).execute();
        if (resp.isSuccessful()) {
            return resp.body().string();
        }
        return null;
    }

    public void postAsync(String url, String json, Callback callback) throws IOException {
        okHttpClient.newCall(request.builderPostRequest(url, json)).enqueue(callback);
    }


    public String post(String url, Map<String, String> parameters) throws IOException {
        Response resp = okHttpClient.newCall(request.builderPostRequest(url, parameters)).execute();
        if (resp.isSuccessful()) {
            return resp.body().string();
        }
        return null;
    }

    public void postAsync(String url, Map<String, String> parameters, Callback callback) throws IOException {
        okHttpClient.newCall(request.builderPostRequest(url, parameters)).enqueue(callback);
    }

    public OkRequest getRequest() {
        return request;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
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

}
