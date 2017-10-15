package com.edgarxie.githubhands.net;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by dofor on 2017/10/12.
 */

public interface Service {

    @POST("/feedback")
    Observable<ResponseBody> feedback(@Body RequestBody body);
}
