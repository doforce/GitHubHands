package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.net.RetrofitClient;
import com.edgarxie.githubhands.net.Service;
import com.edgarxie.githubhands.ui.interf.IFeedbackView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.utils.android.ToastUtil;
import com.edgarxie.utils.java.StringUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by edgar on 17-5-3.
 */

public class FeedbackP extends BasePresenter<IFeedbackView> {

    public FeedbackP(Context context){
        this.mContext=context;
    }

    public void confirm() {
        if (StringUtil.isBank(mView.getContentText())){
            ToastUtil.show(mContext,"Feedback content cant not be null.");
            return;
        }
        RetrofitClient.instance(Constant.BASE_MAIL_SERVER)
                .create(Service.class)
                .feedback(body(json()))
                .subscribeOn(Schedulers.io())
                .subscribe();
//        Observable.empty()
//                .doOnComplete(() -> OkManager.getInstance()
//                .postJson(Constant.BASE_MAIL_SERVER+"/feedback",json()))
//                .subscribeOn(Schedulers.io())
//                .subscribe();
        mView.finishAty();
        ToastUtil.show(mContext,"Done");

    }

    private RequestBody body(String json){
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

    private String json(){
        Map<String,String> map=new HashMap<>();
        map.put("contact",mView.getContactText());
        map.put("subject","GitHubHands feedback");
        map.put("content",mView.getContentText());
        Gson gson=new Gson();
        return gson.toJson(map);
    }
}
