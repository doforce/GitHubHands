package com.xxdong.ok;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

/**
 * Created by edgar on 17-6-18.
 */

public class OkRequest {
    private Request.Builder builder;

    public OkRequest(){
        builder=new Request.Builder();
    }

    public OkRequest(String headerName,String headerValue){
        this();
        builder.header(headerName,headerValue);
    }

    public OkRequest(Map<String,String> headers){
        this();
        if (headers!=null){
            for (Map.Entry<String,String> entry:headers.entrySet()){
                builder.addHeader(entry.getKey(),entry.getValue());
            }
        }
    }

    protected Request getRequest(String url, Map<String, String> parameters) {
        return builder.get().url(httpUrl(url, parameters)).build();
    }

    protected Request postRequestJson(String url, String json) {
        return builder.post(requestBodyJson(json)).url(url).build();
    }

    protected Request postRequest(String url, Map<String, String> parameters) {
        return builder.post(Util.EMPTY_REQUEST).url(httpUrl(url,parameters)).build();
    }

    protected Request postRequestBody(String url, RequestBody body) {
        return builder.post(body).url(url).build();
    }

    protected Request putRequestJson(String url,String json){
        return builder.put(requestBodyJson(json)).url(url).build();
    }

    protected Request putRequestBody(String url,RequestBody body){
        return builder.put(body).url(url).build();
    }

    protected Request putRequest(String url,Map<String,String> parameters){
        return builder.put(Util.EMPTY_REQUEST).url(httpUrl(url,parameters)).build();
    }

    protected Request deleteRequestJson(String url,String json){
        return builder.delete(requestBodyJson(json)).url(url).build();
    }

    protected Request deleteRequest(String url,Map<String,String> parameters){
        return builder.delete().url(httpUrl(url,parameters)).build();
    }

    protected Request deleteRequestBody(String url,RequestBody body){
        return builder.delete(body).url(url).build();
    }

    protected Request patchRequestJson(String url,String json){
        return builder.patch(requestBodyJson(json)).url(url).build();
    }

    protected Request patchRequest(String url,Map<String,String> parameters){
        return builder.patch(Util.EMPTY_REQUEST).url(httpUrl(url,parameters)).build();
    }

    protected Request patchRequestBody(String url,RequestBody body){
        return builder.patch(body).url(url).build();
    }

    private RequestBody requestBodyJson(String json){
       return RequestBody.create(okhttp3.MediaType.parse("application/json"), json);
    }

    public static FormBody requestFormBody(Map<String, String> parameters){
        final FormBody.Builder body = new FormBody.Builder();
        if (parameters != null) {
            for (Map.Entry<String,String> entry:parameters.entrySet()){
                body.add(entry.getKey(),entry.getValue());
            }
        }
        return body.build();
    }

    public static HttpUrl httpUrl(String url, Map<String, String> parameters) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (parameters != null) {
            for (Map.Entry<String,String> entry:parameters.entrySet()){
                builder.addQueryParameter(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }
}
