package com.edgarxie.bindingtest.aty;

import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.edgarxie.bindingtest.R;
import com.edgarxie.bindingtest.databinding.ActivityAnimationBinding;

/**
 * Created by edgar on 17-4-10.
 */

public class AnimationActivity extends AppCompatActivity {
    private ActivityAnimationBinding mBinding;

    public class Presenter{
      public void onCheckedChanged(View buttonView,boolean isChecked){
           mBinding.setShowImage(isChecked);
       }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_animation);
        mBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                ViewGroup v= (ViewGroup) mBinding.getRoot();
                TransitionManager.beginDelayedTransition(v);
                return true;
            }
        });
        mBinding.setPresenter(new Presenter());

    }
}
