package com.edgarxie.bindingtest.aty;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.edgarxie.bindingtest.R;
import com.edgarxie.bindingtest.databinding.ActivityTwoWayBinding;
import com.edgarxie.bindingtest.model.FormModel;

/**
 * Created by edgar on 17-4-6.
 */

public class TowWayActivity extends AppCompatActivity {

    private ActivityTwoWayBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_two_way);
        FormModel form = new FormModel("edgar","1233");
        form.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Toast.makeText(TowWayActivity.this,String.valueOf(i),Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mBinding.setForm(form);
    }
}
