package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.util.Log;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.model.bean.TrendingBean;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.githubhands.net.RetrofitClient;
import com.edgarxie.githubhands.ui.interf.IRepoView;
import com.edgarxie.githubhands.util.NetConstants;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by edgar on 17-6-16.
 */

public class RepoPresenter extends BasePresenter<IRepoView> {
    private TrendingRepoAdapter mAdapter;
    private Context mContext;
    private List<TrendingRepoBean> mRepos=new ArrayList<>();
    private static final String TAG = "RepoPresenter";

    public RepoPresenter(Context context){
        mContext=context;
        mAdapter=new TrendingRepoAdapter(mContext);
    }

    public void requestRepo(String language,String frequency) {
        RetrofitClient retrofitClient=new RetrofitClient(NetConstants.BASE_TRENDING_URL);
        retrofitClient.getRectService().getTrendingRepo(formatLanguage(language),frequency)
        .map(TrendingBean::getItems)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TrendingRepoBean>>() {
                    @Override
                    public void onSubscribe(Subscription s) {}

                    @Override
                    public void onNext(List<TrendingRepoBean> trendingRepoBeen) {
                        mRepos=trendingRepoBeen;
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG,t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mAdapter.setList(mRepos);
                        mView.setRecyclerAdapter(mAdapter);
                    }
                });
    }

    public void onRefresh() {

    }

    //当frequency或者tab中的language改变时，重新价值trending项目
    public void reloadRepo(String language, String frequency) {
        requestRepo(language,frequency);
    }

    private String formatLanguage(String language){
        if (language.equals("C#")){
            language="c%23";
        }else if (language.equals("F#")){
            language="f%23";
        }
        return language;
    }
}
