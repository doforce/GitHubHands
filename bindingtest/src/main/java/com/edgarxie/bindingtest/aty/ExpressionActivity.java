package com.edgarxie.bindingtest.aty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edgarxie.bindingtest.R;
import com.edgarxie.bindingtest.databinding.ActivityExpressionBinding;
import com.edgarxie.bindingtest.model.Employee;

import java.util.Random;

/**
 * Created by edgar on 17-4-6.
 */

public class ExpressionActivity extends AppCompatActivity {
    private ActivityExpressionBinding mBinding;
    private Random random=new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_expression);
        Employee employee=new Employee("Edgar","xie",random.nextBoolean());
        employee.setAvatar("https://github.com/bumptech/glide/raw/master/static/glide_logo.png");
        mBinding.setEmployee(employee);
    }
}
