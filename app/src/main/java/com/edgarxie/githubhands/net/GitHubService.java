package com.edgarxie.githubhands.net;

import com.edgarxie.githubhands.model.bean.TrendingBean;
import com.edgarxie.githubhands.model.bean.TrendingDevBean;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.githubhands.util.NetConstants;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by edgar on 17-6-16.
 */

public interface GitHubService {

    @GET(NetConstants.REPO+"{language}")
    Flowable<TrendingBean<TrendingRepoBean>> getTrendingRepo(
            @Path("language") String language,
            @Query("since") String since
    );

    @GET(NetConstants.DEV)
    Flowable<TrendingBean<TrendingDevBean>> getTrendingDev(
            @Query("since") String since
    );

}
