package com.xxdong.ok;

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
    private Request.Builder builder;

    private OkRequest(){
        builder=new Request.Builder();
    }

    private OkRequest(String headerName,String headerValue){
        this();
        builder.addHeader(headerName,headerValue);
    }

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

    public static OkRequest getInstance(String headerName,String headerValue){
        if (null==okRequest){
            synchronized (OkRequest.class){
                if (null==okRequest){
                    okRequest=new OkRequest(headerName,headerValue);
                }
            }
        }
        return okRequest;
    }


    protected Request getRequest(String url, Map<String, String> parameters) {
        return builder.get().url(httpUrl(url, parameters)).build();
    }

    protected Request postRequestJson(String url, String json) {
        return builder.post(requestBody(json)).url(url).build();
    }


    protected Request postRequest(String url, Map<String, String> parameters) {
        return builder.post(requestBody(parameters)).url(url).build();
    }

    protected Request putRequestJson(String url,String json){
        return builder.put(requestBody(json)).url(url).build();
    }

    protected Request putRequest(String url,Map<String,String> parameters){
        return builder.put(requestBody(parameters)).url(url).build();
    }

    protected Request deleteRequestJson(String url,String json){
        return builder.delete(requestBody(json)).url(url).build();
    }

    protected Request deleteRequest(String url,Map<String,String> parameters){
        return builder.delete(requestBody(parameters)).url(url).build();
    }

    protected Request patchRequestJson(String url,String json){
        return builder.patch(requestBody(json)).url(url).build();
    }

    protected Request patchRequestJson(String url,Map<String,String> parameters){
        return builder.patch(requestBody(parameters)).url(url).build();
    }


    private RequestBody requestBody(String json){
       return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
    }

    private FormBody requestBody(Map<String, String> parameters){
        final FormBody.Builder body = new FormBody.Builder();
        if (parameters != null) {
            for (Map.Entry<String,String> entry:parameters.entrySet()){
                body.add(entry.getKey(),entry.getValue());
            }
        }
        return body.build();
    }

    private HttpUrl httpUrl(String url, Map<String, String> parameters) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (parameters != null) {
            for (Map.Entry<String,String> entry:parameters.entrySet()){
                builder.addQueryParameter(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }
}
