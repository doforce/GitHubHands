package com.xxdong.ok;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by edgar on 17-6-18.
 */

public class OkRequest {
    private static volatile OkRequest okRequest;

    private OkRequest(){}

    public static OkRequest getInstance(){
        if (null==okRequest){
            synchronized (OkRequest.class){
                if (null==okRequest){
                    okRequest=new OkRequest();
                }
            }
        }
        return okRequest;
    }

    protected Request buildGetRequest(String url, Map<String, String> parameters) {
        return new Request.Builder()
                .get()
                .url(buildHttpUrl(url, parameters))
                .build();
    }

    protected Request builderPostRequest(String url, String json) {
        RequestBody body = RequestBody
                .create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
        return new Request.Builder()
                .post(body)
                .url(url).build();
    }



    protected Request builderPostRequest(String url, Map<String, String> parameters) {
        final FormBody.Builder body = new FormBody.Builder();
        if (parameters != null) {
            for (Map.Entry<String,String> entry:parameters.entrySet()){
                body.add(entry.getKey(),entry.getValue());
            }
        }
        return new Request.Builder()
                .post(body.build())
                .url(url).build();
    }

    private HttpUrl buildHttpUrl(String url, Map<String, String> parameters) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (parameters != null) {
            for (Map.Entry<String,String> entry:parameters.entrySet()){
                builder.addQueryParameter(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }
}
