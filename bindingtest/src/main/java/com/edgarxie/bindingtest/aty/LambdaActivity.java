package com.edgarxie.bindingtest.aty;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edgarxie.bindingtest.R;
import com.edgarxie.bindingtest.ToastUtils;
import com.edgarxie.bindingtest.databinding.ActivityLambdaBinding;
import com.edgarxie.bindingtest.model.Employee;

/**
 * Created by edgar on 17-4-10.
 */

public class LambdaActivity extends AppCompatActivity {
    private ActivityLambdaBinding mBinding;

    public class Presenter{
        public void onEmployeeClick(Employee employee){
            ToastUtils.show(LambdaActivity.this,"onEmployeeClick");
        }

        public void onEmployeeLongClick(Employee employee, Context context){
            ToastUtils.show(LambdaActivity.this,"onEmployeeLongClick");
        }

        public void onFocusChange(Employee employee){
            ToastUtils.show(LambdaActivity.this,"onFocusChange");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_lambda);
        mBinding.setEmployee(new Employee("EDDDD","DDDD",false));
        mBinding.setPresenter(new Presenter());
    }
}
