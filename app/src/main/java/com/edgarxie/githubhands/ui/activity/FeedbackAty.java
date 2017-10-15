package com.edgarxie.githubhands.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.FeedbackP;
import com.edgarxie.githubhands.ui.interf.IFeedbackView;

/**
 * Created by dofor on 2017/10/12.
 */

public class FeedbackAty extends BaseActivity<FeedbackP>  implements IFeedbackView{
    private ImageView back;
    private TextView title;
    private ImageView correct;
    private EditText contact;
    private EditText content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mPresenter=new FeedbackP(this);
        initViews();
    }

    private void initViews(){
        back= (ImageView) findViewById(R.id.toolbar_back);
        title= (TextView) findViewById(R.id.toolbar_title);
        correct= (ImageView) findViewById(R.id.toolbar_correct);
        contact= (EditText) findViewById(R.id.feedback_contact);
        content= (EditText) findViewById(R.id.feedback_content);

        title.setText(R.string.feedback);
        back.setOnClickListener(v -> finish());
        correct.setOnClickListener(v -> mPresenter.confirm());
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }

    @Override
    public String getContactText() {
        return contact.getText().toString().trim();
    }

    @Override
    public String getContentText() {
        return content.getText().toString().trim();
    }

    @Override
    public void finishAty() {
        finish();
    }
}
